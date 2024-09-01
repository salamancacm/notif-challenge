package com.gila.notifchallenge.enums;

public enum Channel {
    PUSH_NOTIFICATION("Push Notification"),
    SMS("Sms"),
    EMAIL("Email");

    private final String displayName;
    Channel(String displayName) {
        this.displayName = displayName;
    }

}
