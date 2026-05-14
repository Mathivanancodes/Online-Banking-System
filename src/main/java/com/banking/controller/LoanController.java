package com.banking.controller;

import com.banking.dto.request.LoanApplyRequest;
import com.banking.dto.response.LoanResponse;
import com.banking.security.JwtUtil;
import com.banking.service.LoanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;
    private final JwtUtil jwtUtil;

    @PostMapping("/apply")
    public LoanResponse applyLoan(@Valid @RequestBody LoanApplyRequest request, HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization").substring(7);
        UUID userId = UUID.fromString(jwtUtil.extractUserId(token));
        return loanService.applyLoan(userId, request);
    }
}
