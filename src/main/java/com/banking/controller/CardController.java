package com.banking.controller;

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
