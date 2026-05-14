package com.banking.service.impl;

import com.banking.dto.response.PageResponse;
import com.banking.dto.response.UserProfileResponse;
import com.banking.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserProfileResponse getUserProfile(UUID userId) { return null; }
    @Override
    public void updateProfile(UUID userId, Object request) {}
    @Override
    public PageResponse<UserProfileResponse> getAllUsers(Pageable pageable) { return null; }
}
