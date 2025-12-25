package model.dto;

import model.User;

public record MessageSendDto(
        User sender,
        Object message
) {
}
