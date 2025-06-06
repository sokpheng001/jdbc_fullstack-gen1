package model.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record PostResponseDto(
        String uuid,
        String postTitle,
        String postDescription,
        Set<String> imageUrls,
        UserResponseDto responseDto
) {
}
