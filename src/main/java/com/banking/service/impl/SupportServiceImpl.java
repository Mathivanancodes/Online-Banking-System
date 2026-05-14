package com.banking.service.impl;

import com.banking.service.SupportService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class SupportServiceImpl implements SupportService {
    @Override
    public void createTicket(UUID userId, Object request) {}
}
