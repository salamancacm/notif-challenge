package com.gila.notifchallenge.controllers;

import com.gila.notifchallenge.dto.MessageRequestDTO;
import com.gila.notifchallenge.models.NotificationLog;
import com.gila.notifchallenge.services.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendNotification_validRequest_returnsSuccessMessage() {
        MessageRequestDTO requestDTO = new MessageRequestDTO("Sports", "Game tonight");
        doNothing().when(notificationService).sendMessage(requestDTO.getCategory(), requestDTO.getMessage());

        ResponseEntity<String> response = notificationController.sendNotification(requestDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Notification sent successfully", response.getBody());
        verify(notificationService, times(1)).sendMessage(requestDTO.getCategory(), requestDTO.getMessage());
    }

    @Test
    void getLogs_returnsListOfLogs() {
        List<NotificationLog> logs = Collections.singletonList(new NotificationLog());
        when(notificationService.getAllLogs()).thenReturn(logs);

        ResponseEntity<List<NotificationLog>> response = notificationController.getLogs();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(logs, response.getBody());
        verify(notificationService, times(1)).getAllLogs();
    }

    @Test
    void getLogs_whenNoLogs_returnsEmptyList() {
        when(notificationService.getAllLogs()).thenReturn(Collections.emptyList());

        ResponseEntity<List<NotificationLog>> response = notificationController.getLogs();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(0, response.getBody().size());
        verify(notificationService, times(1)).getAllLogs();
    }
}
