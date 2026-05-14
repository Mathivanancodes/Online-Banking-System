package com.banking.mapper;

import com.banking.dto.response.*;
import com.banking.entity.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    UserProfileResponse userToProfileResponse(User user);
    AccountResponse accountToResponse(Account account);
    TransactionResponse transactionToResponse(Transaction transaction);
    LoanResponse loanToResponse(Loan loan);
    CardResponse cardToResponse(Card card);
}
