package com.project.ktsession.KTSession.service;

import com.project.ktsession.KTSession.dto.request.LoginRequest;
import com.project.ktsession.KTSession.dto.request.SignupRequest;
import com.project.ktsession.KTSession.dto.request.VerifyOtpRequest;
import com.project.ktsession.KTSession.dto.response.LoginResponse;


public interface UserService {

    /**
     * User Signup
     */
    LoginResponse signup(SignupRequest request);

    /**
     * User Login
     */
    LoginResponse login(LoginRequest request);

    /**
     * Verify Email OTP
     */
    void verifyOtp(VerifyOtpRequest request);

}