package com.project.ktsession.KTSession.service.impl;

import com.project.ktsession.KTSession.dto.request.LoginRequest;
import com.project.ktsession.KTSession.dto.request.SignupRequest;
import com.project.ktsession.KTSession.dto.response.LoginResponse;
import com.project.ktsession.KTSession.entity.User;
import com.project.ktsession.KTSession.exception.BadRequestException;
import com.project.ktsession.KTSession.exception.ResourceNotFoundException;
import com.project.ktsession.KTSession.repository.UserRepository;
import com.project.ktsession.KTSession.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

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
        user.setEnabled(true);

        repository.save(user);

        return new LoginResponse(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                "Registration Successful"
        );
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = repository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid Username"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new BadRequestException("Invalid Password");
        }

        LoginResponse response = modelMapper.map(user, LoginResponse.class);
        response.setMessage("Login Successful");
        return response;
    }
}