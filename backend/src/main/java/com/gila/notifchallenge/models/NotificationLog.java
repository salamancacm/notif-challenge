package com.gila.notifchallenge.models;

import com.gila.notifchallenge.enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String messageCategory;

    private String notificationType;
    private String userEmail;
    private String userPhoneNumber;
    private LocalDateTime timestamp;

    private String details;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;
}
