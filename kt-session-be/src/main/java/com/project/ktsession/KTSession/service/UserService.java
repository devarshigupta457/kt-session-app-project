package com.project.ktsession.KTSession.service;

import com.project.ktsession.KTSession.dto.request.LoginRequest;
import com.project.ktsession.KTSession.dto.request.SignupRequest;
import com.project.ktsession.KTSession.dto.response.LoginResponse;

public interface UserService {

    LoginResponse signup(SignupRequest request);

    LoginResponse login(LoginRequest request);

}