package com.gila.notifchallenge.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class MessageRequestDTO {

    @NotNull(message = "Category cannot be null")
    private String category;

    @NotNull(message = "Message cannot be null")
    private String message;

    public MessageRequestDTO() {}

    public MessageRequestDTO(String category, String message) {
        this.category = category;
        this.message = message;
    }

}
