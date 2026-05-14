package com.banking.service;

import com.banking.dto.request.LoginRequest;
import com.banking.dto.request.RegisterRequest;
import com.banking.dto.response.AuthResponse;

public interface AuthService {
    void register(RegisterRequest request);
    void verifyEmail(String token);
    AuthResponse login(LoginRequest request);
    AuthResponse refreshToken(String token);
    void resetPassword(String token, String newPassword);
}
