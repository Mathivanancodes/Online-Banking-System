package com.banking.service;

import java.util.UUID;

public interface SupportService {
    void createTicket(UUID userId, Object request);
}
