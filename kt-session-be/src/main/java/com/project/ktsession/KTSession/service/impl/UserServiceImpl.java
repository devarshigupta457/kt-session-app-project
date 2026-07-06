package com.project.ktsession.KTSession.service.impl;

import com.project.ktsession.KTSession.dto.request.LoginRequest;
import com.project.ktsession.KTSession.dto.request.SignupRequest;
import com.project.ktsession.KTSession.dto.request.VerifyOtpRequest;
import com.project.ktsession.KTSession.dto.response.LoginResponse;
import com.project.ktsession.KTSession.entity.User;
import com.project.ktsession.KTSession.dto.event.UserRegisteredEvent;
import com.project.ktsession.KTSession.exception.BadRequestException;
import com.project.ktsession.KTSession.exception.ResourceNotFoundException;
import com.project.ktsession.KTSession.service.EmailService;
import com.project.ktsession.KTSession.service.kafka.KafkaProducerService;
import com.project.ktsession.KTSession.repository.UserRepository;
import com.project.ktsession.KTSession.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final KafkaProducerService kafkaProducerService;
    private final EmailService emailService;

    private String generateOtp() {
        return String.format("%06d",
                new java.util.Random().nextInt(1000000));
    }

    @Override
    public LoginResponse signup(SignupRequest request) {

        if (repository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username already exists");
        }

        if (repository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        User user = modelMapper.map(request, User.class);

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        user.setEnabled(false);
        user.setEmailVerified(false);


        String otp = generateOtp();

        user.setEmailOtp(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(10));

        User savedUser = repository.save(user);

        emailService.sendOtpEmail(
                savedUser.getEmail(),
                savedUser.getFullName(),
                otp
        );

        LoginResponse response = modelMapper.map(savedUser, LoginResponse.class);
        response.setMessage("OTP sent successfully to your email.");

        return response;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = repository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid Username"));


        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid Password");
        }

        if (!user.isEmailVerified()) {
            throw new BadRequestException(
                    "Please verify your email first.");
        }

        LoginResponse response = modelMapper.map(user, LoginResponse.class);
        response.setMessage("Login Successful");

        return response;

    }

    @Override
    public void verifyOtp(VerifyOtpRequest request) {

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new BadRequestException("User not found"));

        if (user.getOtpExpiry() == null ||
                user.getOtpExpiry().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("OTP has expired.");
        }

        if (user.getEmailOtp() == null ||
                !user.getEmailOtp().equals(request.getOtp())) {
            throw new BadRequestException("Invalid OTP.");
        }

        // Activate account
        user.setEnabled(true);
        user.setEmailVerified(true);

        // Clear OTP after successful verification
        user.setEmailOtp(null);
        user.setOtpExpiry(null);

        repository.save(user);

        // Send Welcome Email
        emailService.sendRegistrationEmail(
                user.getEmail(),
                user.getFullName()
        );

        // Publish Kafka Event (Optional)
        // kafkaProducerService.publish(
        //         new UserRegisteredEvent(
        //                 user.getFullName(),
        //                 user.getEmail()
        //         )
        // );
    }


}