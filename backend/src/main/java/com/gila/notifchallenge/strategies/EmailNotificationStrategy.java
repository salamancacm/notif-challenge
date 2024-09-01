package com.gila.notifchallenge.strategies;

import com.gila.notifchallenge.enums.Channel;
import com.gila.notifchallenge.models.Message;
import com.gila.notifchallenge.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationStrategy implements NotificationStrategy {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationStrategy.class);

    @Override
    public boolean supports(Channel channel) {
        return channel == Channel.EMAIL;
    }

    @Override
    public void sendNotification(User user, Message message) {
        try {
            validateUser(user);
        } catch (IllegalArgumentException e) {
            logger.error("Failed to send email: Invalid user data - {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to send email to {}: {}", user.getEmail(), e.getMessage());
        }
    }

    private void validateUser(User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User email is invalid or empty.");
        }
    }
}
