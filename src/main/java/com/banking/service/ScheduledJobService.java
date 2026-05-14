package com.banking.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledJobService {

    @Scheduled(cron = "0 0 * * * *")
    public void processScheduledTransfers() {
        log.info("Processing scheduled transfers...");
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void processFixedDepositMaturity() {
        log.info("Processing FD maturities...");
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void creditSavingsInterest() {
        log.info("Crediting savings interest...");
    }

    @Scheduled(cron = "0 0 2 * * *")
    public void checkAccountDormancy() {
        log.info("Checking account dormancy...");
    }

    @Scheduled(cron = "0 0 3 * * *")
    public void autoDebitEMI() {
        log.info("Auto debiting EMIs...");
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void sendEmiDueReminders() {
        log.info("Sending EMI due reminders...");
    }
    
    @Scheduled(cron = "0 0 9 * * *")
    public void sendFdMaturityReminders() {
        log.info("Sending FD maturity reminders...");
    }
}
