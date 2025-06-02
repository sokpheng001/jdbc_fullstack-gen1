package model.dto;


public record UserUpdateDto(
        String userName,
        String email,
        String password
) { }
