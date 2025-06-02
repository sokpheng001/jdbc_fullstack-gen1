package model.dto;

import lombok.Builder;

@Builder
public record UserResponseDto(
        String uuid,
        String userName,
        String email,
        Boolean isDeleted
) { }
