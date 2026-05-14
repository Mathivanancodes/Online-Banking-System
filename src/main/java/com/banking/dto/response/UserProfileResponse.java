package com.banking.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserProfileResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String role;
    private String status;
    private String profilePicture;
    private LocalDate dateOfBirth;
    private boolean twoFactorEnabled;
}
