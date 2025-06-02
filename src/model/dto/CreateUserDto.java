package model.dto;

import lombok.Builder;

@Builder
public record CreateUserDto(
        String userName,
        String email,
        String password
) {
}
