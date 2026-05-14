package com.banking.service;

import com.banking.dto.request.CardRequest;
import com.banking.dto.response.CardResponse;
import java.util.UUID;

public interface CardService {
    CardResponse requestCard(UUID userId, CardRequest request);
}
