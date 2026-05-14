package com.banking.controller;

import com.banking.dto.response.UserProfileResponse;
import com.banking.security.JwtUtil;
import com.banking.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class CustomerUserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping("/profile")
    public UserProfileResponse getProfile(HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization").substring(7);
        UUID userId = UUID.fromString(jwtUtil.extractUserId(token));
        return userService.getUserProfile(userId);
    }
}
