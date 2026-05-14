package com.banking.controller;

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
