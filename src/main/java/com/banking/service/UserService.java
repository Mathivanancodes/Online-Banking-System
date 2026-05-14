package com.banking.service;

import com.banking.dto.response.PageResponse;
import com.banking.dto.response.UserProfileResponse;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface UserService {
    UserProfileResponse getUserProfile(UUID userId);
    void updateProfile(UUID userId, Object request);
    PageResponse<UserProfileResponse> getAllUsers(Pageable pageable);
}
