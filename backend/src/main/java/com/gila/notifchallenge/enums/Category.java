package com.gila.notifchallenge.enums;

import lombok.Getter;

@Getter
public enum Category {
    SPORTS("Sports"),
    FINANCE("Finance"),
    MOVIES("Movies");

    private final String displayName;
    Category(String displayName) {
        this.displayName = displayName;
    }

}


