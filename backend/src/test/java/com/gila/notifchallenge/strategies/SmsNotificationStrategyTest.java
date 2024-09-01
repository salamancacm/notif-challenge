package com.gila.notifchallenge.strategies;

import com.gila.notifchallenge.enums.Channel;
import com.gila.notifchallenge.models.Message;
import com.gila.notifchallenge.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SmsNotificationStrategyTest {

    private SmsNotificationStrategy smsNotificationStrategy;
    private User mockUser;
    private Message mockMessage;

    @BeforeEach
    public void setUp() {
        smsNotificationStrategy = new SmsNotificationStrategy();
        mockUser = mock(User.class);
        mockMessage = mock(Message.class);
    }

    @Test
    public void supports_withSmsChannel_returnsTrue() {
        assertTrue(smsNotificationStrategy.supports(Channel.SMS));
    }

    @Test
    public void supports_withNonSmsChannel_returnsFalse() {
        assertFalse(smsNotificationStrategy.supports(Channel.EMAIL));
        assertFalse(smsNotificationStrategy.supports(Channel.PUSH_NOTIFICATION));
    }

    @Test
    public void sendNotification_withValidUser_callsValidateUser() {
        when(mockUser.getPhoneNumber()).thenReturn("1234567890");

        smsNotificationStrategy.sendNotification(mockUser, mockMessage);

        verify(mockUser, atLeastOnce()).getPhoneNumber();
    }

    @Test
    public void sendNotification_withInvalidUser_logsError() {
        when(mockUser.getPhoneNumber()).thenReturn("");

        smsNotificationStrategy.sendNotification(mockUser, mockMessage);

        verify(mockUser, atLeastOnce()).getPhoneNumber();
    }
}
