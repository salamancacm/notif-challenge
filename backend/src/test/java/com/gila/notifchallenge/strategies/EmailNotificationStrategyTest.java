package com.gila.notifchallenge.strategies;

import com.gila.notifchallenge.enums.Channel;
import com.gila.notifchallenge.models.Message;
import com.gila.notifchallenge.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class EmailNotificationStrategyTest {

    private EmailNotificationStrategy emailNotificationStrategy;
    private User mockUser;
    private Message mockMessage;

    @BeforeEach
    public void setUp() {
        emailNotificationStrategy = new EmailNotificationStrategy();
        mockUser = mock(User.class);
        mockMessage = mock(Message.class);
    }

    @Test
    public void supports_withEmailChannel_returnsTrue() {
        assertTrue(emailNotificationStrategy.supports(Channel.EMAIL));
    }

    @Test
    public void supports_withNonEmailChannel_returnsFalse() {
        assertFalse(emailNotificationStrategy.supports(Channel.SMS));
        assertFalse(emailNotificationStrategy.supports(Channel.PUSH_NOTIFICATION));
    }

    @Test
    public void sendNotification_withValidUser_callsValidateUser() {
        when(mockUser.getEmail()).thenReturn("user@example.com");

        emailNotificationStrategy.sendNotification(mockUser, mockMessage);

        verify(mockUser, atLeastOnce()).getEmail();
    }

    @Test
    public void sendNotification_withInvalidUser_logsError() {
        when(mockUser.getEmail()).thenReturn("");

        emailNotificationStrategy.sendNotification(mockUser, mockMessage);

        verify(mockUser, atLeastOnce()).getEmail();
    }
}
