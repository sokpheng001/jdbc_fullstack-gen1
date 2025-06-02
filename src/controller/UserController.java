package controller;

import model.dto.CreateUserDto;
import model.dto.UserResponseDto;
import model.dto.UserUpdateDto;
import model.entities.User;
import model.service.UserServiceImpl;
import java.util.List;

public class UserController {
    private final UserServiceImpl userServiceImpl =
            new UserServiceImpl();
    public UserResponseDto createNewUser(CreateUserDto createUserDto){
        return userServiceImpl.addNewUser(createUserDto);
    }
    public List<UserResponseDto> getAllUsers(){
        return userServiceImpl.getAllUsers();
    }
    public UserResponseDto getUserByUuid(String uuid){
        return userServiceImpl.findUserByUuid(uuid);
    }
    public UserResponseDto updateUserByUuid(String uuid, UserUpdateDto userUpdateDto){
        return userServiceImpl.updateUserByUuid(uuid, userUpdateDto);
    }
    public Integer deleteUserByUuid(String uuid){
        return userServiceImpl.deleteUserByUuid(uuid);
    }
}
