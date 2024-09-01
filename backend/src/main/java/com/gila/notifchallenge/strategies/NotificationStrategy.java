package com.gila.notifchallenge.strategies;

import com.gila.notifchallenge.enums.Channel;
import com.gila.notifchallenge.models.Message;
import com.gila.notifchallenge.models.User;

public interface NotificationStrategy {
    boolean supports(Channel channel);

    void sendNotification(User user, Message message);
}
