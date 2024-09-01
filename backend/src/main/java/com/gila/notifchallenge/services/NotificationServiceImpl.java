package com.gila.notifchallenge.services;

import com.gila.notifchallenge.enums.Category;
import com.gila.notifchallenge.enums.Channel;
import com.gila.notifchallenge.exceptions.InvalidCategoryException;
import com.gila.notifchallenge.exceptions.UnsupportedChannelException;
import com.gila.notifchallenge.models.Message;
import com.gila.notifchallenge.models.NotificationLog;
import com.gila.notifchallenge.models.User;
import com.gila.notifchallenge.repositories.MessageRepository;
import com.gila.notifchallenge.repositories.NotificationLogRepository;
import com.gila.notifchallenge.repositories.UserRepository;
import com.gila.notifchallenge.strategies.NotificationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final NotificationLogRepository notificationLogRepository;
    private final List<NotificationStrategy> notificationStrategies;

    public NotificationServiceImpl(UserRepository userRepository,
                                   MessageRepository messageRepository,
                                   NotificationLogRepository notificationLogRepository,
                                   List<NotificationStrategy> notificationStrategies) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.notificationLogRepository = notificationLogRepository;
        this.notificationStrategies = notificationStrategies;
    }

    @Transactional
    @Override
    public void sendMessage(String categoryStr, String content) {
        Category category = getCategoryFromString(categoryStr);
        Message message = saveMessage(categoryStr, content);
        List<User> users = userRepository.findBySubscribedCategoriesContains(category);

        for (User user : users) {
            sendNotificationsToUser(user, message, category);
        }
    }

    @Override
    public List<NotificationLog> getAllLogs() {
        return notificationLogRepository.findAllByOrderByTimestampDesc();
    }

    private Category getCategoryFromString(String categoryStr) {
        try {
            return Category.valueOf(categoryStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCategoryException("Invalid category: " + categoryStr);
        }
    }

    private Message saveMessage(String category, String content) {
        return messageRepository.save(Message.builder()
                .category(category)
                .content(content)
                .build());
    }

    private void sendNotificationsToUser(User user, Message message, Category category) {
        for (Channel channel : user.getNotificationChannels()) {
            try {
                NotificationStrategy strategy = getNotificationStrategy(channel);
                strategy.sendNotification(user, message);
                logNotification(user, category, channel, message);
            } catch (IllegalArgumentException e) {
                logger.error("Unsupported channel {} for user {}", channel, user.getEmail(), e);
            } catch (Exception e) {
                logger.error("Failed to send notification to user {} on channel {}", user.getEmail(), channel, e);
            }
        }
    }

    private NotificationStrategy getNotificationStrategy(Channel channel) {
        return notificationStrategies.stream()
                .filter(s -> s.supports(channel))
                .findFirst()
                .orElseThrow(() -> new UnsupportedChannelException("Unsupported channel: " + channel.name()));
    }


    private void logNotification(User user, Category category, Channel channel, Message message) {
        notificationLogRepository.save(NotificationLog.builder()
                .messageCategory(category.name())
                .notificationType(channel.name())
                .userEmail(user.getEmail())
                .userPhoneNumber(user.getPhoneNumber())
                .timestamp(LocalDateTime.now())
                .details("Notification sent successfully")
                .message(message)
                .build());
    }
}
