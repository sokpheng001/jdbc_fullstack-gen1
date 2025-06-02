package mapper;

import model.dto.CreateUserDto;
import model.dto.UserResponseDto;
import model.entities.User;

import java.util.Random;
import java.util.UUID;

/**
 * <p>Use this class to map (convert) from one object type to another object type</p>
 */

public class UserMapper {
    public static User fromCreateUserDtoToUser(CreateUserDto createUserDto){
        return User.builder()
                .id(new Random().nextInt(999999999))
                .uuid(UUID.randomUUID().toString())
                .userName(createUserDto.userName())
                .email(createUserDto.email())
                .password(createUserDto.password())
                .isDeleted(false)
                .build();
    }
    public static UserResponseDto fromUserToUserResponseDto(User user){
        return UserResponseDto
                .builder()
                .uuid(user.getUuid())
                .userName(user.getUserName())
                .email(user.getEmail())
                .isDeleted(user.getIsDeleted())
                .build();
    }
}
