package com.project.ktsession.KTSession.service.impl;

import com.project.ktsession.KTSession.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendRegistrationEmail(String email, String fullName) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Welcome to KT Session – Registration Successful");

        message.setText(
                "Dear " + fullName + ",\n\n" +
                        "Welcome to KT Session!\n\n" +
                        "Your account has been created successfully, and we're excited to have you as part of our learning community.\n\n" +
                        "You can now log in and start exploring high-quality technical sessions, courses, and resources designed to enhance your skills and accelerate your career.\n\n" +
                        "We're committed to providing you with an engaging and seamless learning experience.\n\n" +
                        "If you have any questions or need assistance, feel free to reach out to our support team.\n\n" +
                        "Happy Learning!\n\n" +
                        "Best Regards,\n\n" +
                        "KT Session Team\n" +
                        "Empowering Learning, One Session at a Time."
        );

        mailSender.send(message);
    }
}