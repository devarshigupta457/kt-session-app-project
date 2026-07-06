package com.project.ktsession.KTSession.service;

public interface EmailService {

    void sendRegistrationEmail(String email, String fullName);

    void sendOtpEmail(String email, String fullName, String otp);
}