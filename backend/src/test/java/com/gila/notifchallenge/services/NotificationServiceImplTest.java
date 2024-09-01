package com.gila.notifchallenge.services;

import com.gila.notifchallenge.enums.Category;
import com.gila.notifchallenge.enums.Channel;
import com.gila.notifchallenge.exceptions.InvalidCategoryException;
import com.gila.notifchallenge.models.Message;
import com.gila.notifchallenge.models.NotificationLog;
import com.gila.notifchallenge.models.User;
import com.gila.notifchallenge.repositories.MessageRepository;
import com.gila.notifchallenge.repositories.NotificationLogRepository;
import com.gila.notifchallenge.repositories.UserRepository;
import com.gila.notifchallenge.strategies.NotificationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private NotificationLogRepository notificationLogRepository;

    @Mock
    private NotificationStrategy pushNotificationStrategy;

    @Mock
    private NotificationStrategy smsStrategy;

    @Mock
    private NotificationStrategy emailStrategy;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Captor
    private ArgumentCaptor<Message> messageCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialize the notificationStrategies list with mocks
        notificationService = new NotificationServiceImpl(
                userRepository,
                messageRepository,
                notificationLogRepository,
                Arrays.asList(pushNotificationStrategy, smsStrategy, emailStrategy)
        );

        when(pushNotificationStrategy.supports(Channel.PUSH_NOTIFICATION)).thenReturn(true);
        when(smsStrategy.supports(Channel.SMS)).thenReturn(true);
        when(emailStrategy.supports(Channel.EMAIL)).thenReturn(true);
    }

    @Test
    void sendMessage_validCategory_sendsNotifications() {
        String categoryStr = "SPORTS";
        String content = "Match tonight!";
        User user = User.builder()
                .email("user@example.com")
                .notificationChannels(Collections.singletonList(Channel.PUSH_NOTIFICATION))
                .build();
        when(userRepository.findBySubscribedCategoriesContains(Category.SPORTS)).thenReturn(Collections.singletonList(user));
        when(messageRepository.save(any(Message.class))).thenAnswer(i -> i.getArgument(0));

        notificationService.sendMessage(categoryStr, content);

        verify(pushNotificationStrategy, times(1)).sendNotification(eq(user), any(Message.class));
        verify(messageRepository).save(messageCaptor.capture());
        assertEquals(Category.SPORTS.name(), messageCaptor.getValue().getCategory());
        assertEquals(content, messageCaptor.getValue().getContent());
    }

    @Test
    void sendMessage_invalidCategory_throwsInvalidCategoryException() {
        String categoryStr = "INVALID_CATEGORY";
        String content = "Some content";

        InvalidCategoryException exception = assertThrows(InvalidCategoryException.class, () -> {
            notificationService.sendMessage(categoryStr, content);
        });

        assertEquals("Invalid category: INVALID_CATEGORY", exception.getMessage());
        verifyNoInteractions(userRepository, messageRepository, notificationLogRepository, pushNotificationStrategy);
    }

    @Test
    void sendMessage_noUsersSubscribed_doesNotSendNotifications() {
        String categoryStr = "MOVIES";
        String content = "New movie release!";
        when(userRepository.findBySubscribedCategoriesContains(Category.MOVIES)).thenReturn(Collections.emptyList());
        when(messageRepository.save(any(Message.class))).thenAnswer(i -> i.getArgument(0));

        notificationService.sendMessage(categoryStr, content);

        verify(pushNotificationStrategy, never()).sendNotification(any(User.class), any(Message.class));
        verify(messageRepository).save(messageCaptor.capture());
        assertEquals(Category.MOVIES.name(), messageCaptor.getValue().getCategory());
        assertEquals(content, messageCaptor.getValue().getContent());
    }

    @Test
    void sendMessage_withInvalidChannel_logsError() {
        String categoryStr = "SPORTS";
        String content = "Match tonight!";
        User user = User.builder()
                .email("user@example.com")
                .notificationChannels(Arrays.asList(Channel.PUSH_NOTIFICATION, Channel.SMS))
                .build();
        when(userRepository.findBySubscribedCategoriesContains(Category.SPORTS)).thenReturn(Collections.singletonList(user));
        when(messageRepository.save(any(Message.class))).thenAnswer(i -> i.getArgument(0));
        doThrow(new IllegalArgumentException("Unsupported channel: SMS")).when(smsStrategy).sendNotification(any(User.class), any(Message.class));

        notificationService.sendMessage(categoryStr, content);

        verify(pushNotificationStrategy, times(1)).sendNotification(eq(user), any(Message.class));
        verify(smsStrategy, times(1)).sendNotification(eq(user), any(Message.class));
        verify(messageRepository).save(messageCaptor.capture());
        verify(notificationLogRepository, times(1)).save(any(NotificationLog.class));
    }

    @Test
    void getAllLogs_returnsLogs() {
        List<NotificationLog> expectedLogs = Collections.singletonList(NotificationLog.builder().details("Notification sent").build());
        when(notificationLogRepository.findAllByOrderByTimestampDesc()).thenReturn(expectedLogs);

        List<NotificationLog> logs = notificationService.getAllLogs();

        assertEquals(expectedLogs, logs);
        verify(notificationLogRepository).findAllByOrderByTimestampDesc();
    }
}
