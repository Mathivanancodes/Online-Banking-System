import os

base_dir = "src/main/java/com/banking"

files = {
    # Services
    "service/UserService.java": """package com.banking.service;

import com.banking.dto.response.PageResponse;
import com.banking.dto.response.UserProfileResponse;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface UserService {
    UserProfileResponse getUserProfile(UUID userId);
    void updateProfile(UUID userId, Object request);
    PageResponse<UserProfileResponse> getAllUsers(Pageable pageable);
}
""",
    "service/impl/UserServiceImpl.java": """package com.banking.service.impl;

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
""",
    "service/TransferService.java": """package com.banking.service;

import com.banking.dto.request.TransferRequest;
import com.banking.dto.response.TransactionResponse;
import java.util.UUID;

public interface TransferService {
    TransactionResponse transfer(UUID userId, TransferRequest request);
}
""",
    "service/impl/TransferServiceImpl.java": """package com.banking.service.impl;

import com.banking.dto.request.TransferRequest;
import com.banking.dto.response.TransactionResponse;
import com.banking.service.TransferService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class TransferServiceImpl implements TransferService {
    @Override
    public TransactionResponse transfer(UUID userId, TransferRequest request) { return null; }
}
""",
    "service/LoanService.java": """package com.banking.service;

import com.banking.dto.request.LoanApplyRequest;
import com.banking.dto.response.LoanResponse;
import java.util.UUID;

public interface LoanService {
    LoanResponse applyLoan(UUID userId, LoanApplyRequest request);
}
""",
    "service/impl/LoanServiceImpl.java": """package com.banking.service.impl;

import com.banking.dto.request.LoanApplyRequest;
import com.banking.dto.response.LoanResponse;
import com.banking.service.LoanService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class LoanServiceImpl implements LoanService {
    @Override
    public LoanResponse applyLoan(UUID userId, LoanApplyRequest request) { return null; }
}
""",
    "service/CardService.java": """package com.banking.service;

import com.banking.dto.request.CardRequest;
import com.banking.dto.response.CardResponse;
import java.util.UUID;

public interface CardService {
    CardResponse requestCard(UUID userId, CardRequest request);
}
""",
    "service/impl/CardServiceImpl.java": """package com.banking.service.impl;

import com.banking.dto.request.CardRequest;
import com.banking.dto.response.CardResponse;
import com.banking.service.CardService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CardServiceImpl implements CardService {
    @Override
    public CardResponse requestCard(UUID userId, CardRequest request) { return null; }
}
""",
    "service/FixedDepositService.java": """package com.banking.service;

import java.util.UUID;

public interface FixedDepositService {
    void createFixedDeposit(UUID userId, Object request);
}
""",
    "service/impl/FixedDepositServiceImpl.java": """package com.banking.service.impl;

import com.banking.service.FixedDepositService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class FixedDepositServiceImpl implements FixedDepositService {
    @Override
    public void createFixedDeposit(UUID userId, Object request) {}
}
""",
    "service/SupportService.java": """package com.banking.service;

import java.util.UUID;

public interface SupportService {
    void createTicket(UUID userId, Object request);
}
""",
    "service/impl/SupportServiceImpl.java": """package com.banking.service.impl;

import com.banking.service.SupportService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class SupportServiceImpl implements SupportService {
    @Override
    public void createTicket(UUID userId, Object request) {}
}
""",
    
    # Controllers
    "controller/CustomerUserController.java": """package com.banking.controller;

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
""",
    "controller/AdminUserController.java": """package com.banking.controller;

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
""",
    "controller/TransferController.java": """package com.banking.controller;

import com.banking.dto.request.TransferRequest;
import com.banking.dto.response.TransactionResponse;
import com.banking.security.JwtUtil;
import com.banking.service.TransferService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;
    private final JwtUtil jwtUtil;

    @PostMapping("/internal")
    public TransactionResponse transfer(@Valid @RequestBody TransferRequest request, HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization").substring(7);
        UUID userId = UUID.fromString(jwtUtil.extractUserId(token));
        return transferService.transfer(userId, request);
    }
}
""",
    "controller/LoanController.java": """package com.banking.controller;

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
""",
    "controller/CardController.java": """package com.banking.controller;

import com.banking.dto.request.CardRequest;
import com.banking.dto.response.CardResponse;
import com.banking.security.JwtUtil;
import com.banking.service.CardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    private final JwtUtil jwtUtil;

    @PostMapping("/request")
    public CardResponse requestCard(@Valid @RequestBody CardRequest request, HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization").substring(7);
        UUID userId = UUID.fromString(jwtUtil.extractUserId(token));
        return cardService.requestCard(userId, request);
    }
}
""",
    "controller/ReportController.java": """package com.banking.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/reports")
public class ReportController {
    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        return Map.of("totalDeposits", 0, "totalWithdrawals", 0);
    }
}
""",
    "controller/BeneficiaryController.java": """package com.banking.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/beneficiaries")
public class BeneficiaryController {
    @GetMapping
    public List<Object> getBeneficiaries() {
        return List.of();
    }
}
"""
}

for filepath, content in files.items():
    full_path = os.path.join(base_dir, filepath)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w") as f:
        f.write(content)

print("Generated remaining modules successfully.")
