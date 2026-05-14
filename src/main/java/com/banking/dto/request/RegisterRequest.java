package com.banking.dto.request;

import com.banking.util.StrongPassword;
import com.banking.util.ValidPhone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;
    
    @NotBlank
    @Email
    private String email;
    
    @ValidPhone
    private String phone;
    
    @StrongPassword
    private String password;
    
    @Past
    private LocalDate dateOfBirth;
    
    @NotBlank
    private String nationalId;
}
