package com.banking.service.impl;

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
