package model.service;

import mapper.UserMapper;
import model.repository.UserRepositoryImpl;
import model.dto.CreateUserDto;
import model.dto.UserResponseDto;
import model.dto.UserUpdateDto;
import model.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService{
    private final UserRepositoryImpl userRepository = new UserRepositoryImpl();
    public UserResponseDto addNewUser(CreateUserDto createUserDto){
        User user = UserMapper.fromCreateUserDtoToUser(createUserDto);
        return UserMapper.fromUserToUserResponseDto(userRepository.save(user));
    }
    public List<UserResponseDto> getAllUsers(){
        List<UserResponseDto> userResponseDtoList
                 = new ArrayList<>();
        userRepository.findAll()
                .forEach(u->userResponseDtoList.add(UserMapper.fromUserToUserResponseDto(u)));
        return userResponseDtoList;
    }
    public UserResponseDto findUserByUuid(String uuid){
        // logic for validation service
        return UserMapper.fromUserToUserResponseDto(userRepository.findByUserUuid(uuid));
    }
    public Integer deleteUserByUuid(String uuid){
        User user = userRepository.findByUserUuid(uuid);
        if(user!=null){
            return userRepository.delete(user.getId());
        }
        return 0;
    }
    public UserResponseDto updateUserByUuid(String uuid, UserUpdateDto userUpdateDto){
        return UserMapper.fromUserToUserResponseDto(
                userRepository.updateUserByUuid(uuid, userUpdateDto)
        );
    }
}
