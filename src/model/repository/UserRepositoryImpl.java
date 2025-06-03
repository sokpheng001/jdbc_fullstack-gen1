package model.repository;

import model.UserData;
import model.dto.UserUpdateDto;
import model.entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements Repository<User, Integer> {
    private final String dbUrl = "jdbc:postgresql://localhost:5432/social_media_db";
    private final String dbPassword = "123";
    private final String dbUsername = "postgres";
    @Override
    public List<User> findAll() {
        try(Connection con  = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)){
            String sql = """
                    SELECT * FROM users
                    """;
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery(sql);
            List<User> userList = new ArrayList<>();
            while (result.next()){
                User user = new User();
                user.setId(result.getInt("id"));
                user.setUuid(result.getString("uuid"));
                user.setUserName(result.getString("user_name"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setIsDeleted(result.getBoolean("is_deleted"));
                userList.add(user);
            }
            return userList;

        }catch (Exception exception){
            System.out.println("Error during get all users from database");
        }
        return UserData.userList;
    }
    @Override
    public User save(User user) {
        UserData.userList.add(user);
        return user;
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
