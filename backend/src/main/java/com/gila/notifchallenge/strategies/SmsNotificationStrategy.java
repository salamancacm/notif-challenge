package com.gila.notifchallenge.strategies;

import com.gila.notifchallenge.enums.Channel;
import com.gila.notifchallenge.models.Message;
import com.gila.notifchallenge.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationStrategy implements NotificationStrategy {

    private static final Logger logger = LoggerFactory.getLogger(SmsNotificationStrategy.class);

    @Override
    public boolean supports(Channel channel) {
        return channel == Channel.SMS;
    }

    @Override
    public void sendNotification(User user, Message message) {
        try {
            validateUser(user);
        } catch (IllegalArgumentException e) {
            logger.error("Failed to send SMS: Invalid user data - {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to send SMS to {}: {}", user.getPhoneNumber(), e.getMessage());
        }
    }

    private void validateUser(User user) {
        if (user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("User phone number is invalid or empty.");
        }
    }
}