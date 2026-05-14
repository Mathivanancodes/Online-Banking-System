package com.banking.service;

public interface NotificationService {
    void sendEmail(String to, String subject, String body);
    void sendInAppNotification(String userId, String title, String message, String type);
    void sendSms(String phone, String message);
}
