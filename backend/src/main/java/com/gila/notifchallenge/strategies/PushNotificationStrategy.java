package com.gila.notifchallenge.strategies;

import com.gila.notifchallenge.enums.Channel;
import com.gila.notifchallenge.models.Message;
import com.gila.notifchallenge.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PushNotificationStrategy implements NotificationStrategy {

    private static final Logger logger = LoggerFactory.getLogger(PushNotificationStrategy.class);

    @Override
    public boolean supports(Channel channel) {
        return channel == Channel.PUSH_NOTIFICATION;
    }

    @Override
    public void sendNotification(User user, Message message) {
        try {
            validateUser(user);
        } catch (IllegalArgumentException e) {
            logger.error("Failed to send push notification: Invalid user data - {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to send push notification to {}: {}", user.getName(), e.getMessage());
        }
    }

    private void validateUser(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("User name is invalid or empty.");
        }
    }
}
