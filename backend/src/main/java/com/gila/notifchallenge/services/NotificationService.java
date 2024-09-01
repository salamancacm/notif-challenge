package com.gila.notifchallenge.services;

import com.gila.notifchallenge.models.NotificationLog;

import java.util.List;

public interface NotificationService {

    void sendMessage(String category, String content);
    List<NotificationLog> getAllLogs();

}
