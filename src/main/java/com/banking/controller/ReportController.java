package com.banking.controller;

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
