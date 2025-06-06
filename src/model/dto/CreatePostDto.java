package model.dto;

import lombok.Builder;

import java.util.Set;
@Builder
public record CreatePostDto(
        String title,
        String description,
        Set<String> imageUrls,
        String userUuid
) {
}
