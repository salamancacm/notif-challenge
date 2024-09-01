package com.gila.notifchallenge.strategies;

import com.gila.notifchallenge.enums.Channel;
import com.gila.notifchallenge.models.Message;
import com.gila.notifchallenge.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class PushNotificationStrategyTest {

    private PushNotificationStrategy pushNotificationStrategy;
    private User mockUser;
    private Message mockMessage;

    @BeforeEach
    public void setUp() {
        pushNotificationStrategy = new PushNotificationStrategy();
        mockUser = mock(User.class);
        mockMessage = mock(Message.class);
    }

    @Test
    public void supports_withPushNotificationChannel_returnsTrue() {
        assertTrue(pushNotificationStrategy.supports(Channel.PUSH_NOTIFICATION));
    }

    @Test
    public void supports_withNonPushNotificationChannel_returnsFalse() {
        assertFalse(pushNotificationStrategy.supports(Channel.EMAIL));
        assertFalse(pushNotificationStrategy.supports(Channel.SMS));
    }

    @Test
    public void sendNotification_withValidUser_callsValidateUser() {
        when(mockUser.getName()).thenReturn("John Doe");

        pushNotificationStrategy.sendNotification(mockUser, mockMessage);

        verify(mockUser, atLeastOnce()).getName();
    }

    @Test
    public void sendNotification_withInvalidUser_logsError() {
        when(mockUser.getName()).thenReturn("");

        pushNotificationStrategy.sendNotification(mockUser, mockMessage);

        verify(mockUser, atLeastOnce()).getName();
    }

}
