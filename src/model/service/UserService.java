package model.service;


import model.dto.CreateUserDto;
import model.dto.UserResponseDto;
import model.dto.UserUpdateDto;

import java.util.List;

public interface UserService {
    UserResponseDto addNewUser(CreateUserDto createUserDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto findUserByUuid(String uuid);
    Integer deleteUserByUuid(String uuid);
    UserResponseDto updateUserByUuid(String uuid, UserUpdateDto updateDto);
}
