package com.gila.notifchallenge.controllers;

import com.gila.notifchallenge.dto.MessageRequestDTO;
import com.gila.notifchallenge.models.NotificationLog;
import com.gila.notifchallenge.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@Valid @RequestBody MessageRequestDTO messageRequest) {
        notificationService.sendMessage(messageRequest.getCategory(), messageRequest.getMessage());
        return ResponseEntity.ok("Notification sent successfully");
    }


    @GetMapping("/logs")
    public ResponseEntity<List<NotificationLog>> getLogs() {
        List<NotificationLog> logs = notificationService.getAllLogs();
        return ResponseEntity.ok(logs);
    }

}
