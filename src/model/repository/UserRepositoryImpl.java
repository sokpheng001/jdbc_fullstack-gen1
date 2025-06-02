package model.repository;

import model.UserData;
import model.dto.UserUpdateDto;
import model.entities.User;

import java.util.List;

public class UserRepositoryImpl implements Repository<User, Integer> {

    @Override
    public User save(User user) {
        UserData.userList.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return UserData.userList;
    }

    @Override
    public Integer delete(Integer id) {
        User user  =
                UserData.userList
                        .stream()
                        .filter(u->u.getId().equals(id))
                        .findFirst().get();
        UserData.userList.remove(user);
        return 1;
    }
    public User findByUserUuid(String uuid){
        return UserData.userList
                .stream()
                .filter(u->u.getUuid().equals(uuid))
                .findFirst().get();
    }
    public User updateUserByUuid(String uuid, UserUpdateDto userUpdateDto){
        User user = findByUserUuid(uuid);
        if(user!=null){
            //
            UserData.userList.remove(user);
            user.setUserName(userUpdateDto.userName());
            user.setEmail(userUpdateDto.email());
            user.setPassword(userUpdateDto.password());
            UserData.userList.add(user);
            return user;
        }
        return null;
    }
}
