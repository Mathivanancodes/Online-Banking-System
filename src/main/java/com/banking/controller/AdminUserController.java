package com.banking.controller;

import com.banking.dto.response.PageResponse;
import com.banking.dto.response.UserProfileResponse;
import com.banking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService userService;

    @GetMapping
    public PageResponse<UserProfileResponse> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }
}
