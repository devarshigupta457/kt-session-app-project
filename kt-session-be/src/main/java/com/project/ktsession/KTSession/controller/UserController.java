package com.project.ktsession.KTSession.controller;

import com.project.ktsession.KTSession.dto.request.LoginRequest;
import com.project.ktsession.KTSession.dto.request.SignupRequest;
import com.project.ktsession.KTSession.dto.response.ApiResponse;
import com.project.ktsession.KTSession.dto.response.LoginResponse;
import com.project.ktsession.KTSession.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kt-session")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    /**
     * User Signup
     */
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<LoginResponse>> signup(
            @Valid @RequestBody SignupRequest request) {

        LoginResponse response = userService.signup(request);

        ApiResponse<LoginResponse> apiResponse =
                new ApiResponse<>(
                        true,
                        "User registered successfully.",
                        response
                );

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    /**
     * User Login
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
             @RequestBody LoginRequest request) {

        LoginResponse response = userService.login(request);

        ApiResponse<LoginResponse> apiResponse =
                new ApiResponse<>(
                        true,
                        "Login successful.",
                        response
                );

        return ResponseEntity.ok(apiResponse);
    }
}