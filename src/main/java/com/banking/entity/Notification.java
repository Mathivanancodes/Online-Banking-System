package com.banking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String message;
    
    private String notificationType; // TRANSACTION, SECURITY, ACCOUNT, LOAN, CARD, SYSTEM, PROMOTIONAL
    
    private String channel; // EMAIL, SMS, PUSH, IN_APP
    
    @Builder.Default
    private boolean isRead = false;
    
    private LocalDateTime sentAt;
    private LocalDateTime readAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
