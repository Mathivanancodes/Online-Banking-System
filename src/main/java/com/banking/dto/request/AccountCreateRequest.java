package com.banking.dto.request;

import com.banking.enums.AccountType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountCreateRequest {
    @NotNull
    private AccountType accountType;
}
