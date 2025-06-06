package model.repository;

import model.UserData;
import model.dto.UserUpdateDto;
import model.entities.User;
import utils.DatabaseConfigure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements Repository<User, Integer> {
    @Override
    public List<User> findAll() {
        try(Connection con = DatabaseConfigure.getDatabaseConnection()){
            String sql = """
                    SELECT * FROM users""";
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
        try(Connection con = DatabaseConfigure.getDatabaseConnection()){
            String sql = """
                INSERT INTO users (uuid, user_name, email, password, is_deleted)
                VALUES (?,?,?,?,?)
                """;
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1,user.getUuid());
            pre.setString(2, user.getUserName());
            pre.setString(3, user.getEmail());
            pre.setString(4, user.getPassword());
            pre.setBoolean(5, user.getIsDeleted());
            int rowAffected = pre.executeUpdate();
            if(rowAffected>0){
                System.out.println("User has been inserted successfully");
                return user;
            }

        }catch (Exception exception){
            System.err.println("Error during insert data to table user: " + exception.getMessage());
        }
        return user;
    }
    @Override
    public Integer delete(Integer id) {
       try(Connection con = DatabaseConfigure.getDatabaseConnection()){
           String sql = """
                   DELETE FROM users
                   WHERE id = ?
                   """;
           PreparedStatement pre = con.prepareStatement(sql);
           pre.setInt(1, id);
           int rowAffected = pre.executeUpdate();
           if(rowAffected>0){
               System.out.println("DELETED user by ID successfully");
               return rowAffected;
           }
       }catch (Exception exception){
           System.err.println("ERROR during deleting user by ID: " + exception.getMessage());
       }
        return 0;
    }
    public User findByUserUuid(String uuid){
        try(Connection con = DatabaseConfigure.getDatabaseConnection()){
            String sql = """
                    SELECT * FROM users
                    WHERE uuid = ?
                    """;
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, uuid);
            ResultSet resultSet = pre.executeQuery();
            User user = new User();
            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUuid(resultSet.getString("uuid"));
                user.setUserName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setIsDeleted(resultSet.getBoolean("is_deleted"));
            }
            return user;
        }catch (Exception exception){
            System.err.println("ERROR during find user by UUID :" + exception.getMessage());
        }
        return null;
    }
    public User updateUserByUuid(String uuid, UserUpdateDto userUpdateDto){
       try(Connection con = DatabaseConfigure.getDatabaseConnection()){
           String sql1 = """
                   UPDATE users
                   SET user_name = ?, email = ?, password = ?
                   WHERE uuid = ?
                   """;
           String sql2 = """
                   UPDATE users
                   SET user_name = ?, email = ?
                   WHERE uuid = ?
                   """;
           String sql3= """
                   UPDATE users
                   SET user_name = ?, password = ?
                   WHERE uuid = ?
                   """;
           String sql4 = """
                   UPDATE users
                   SET email = ?, password = ?
                   WHERE uuid = ?
                   """;
           String sql5 = """
                   UPDATE users
                   SET user_name = ?
                   WHERE uuid = ?
                   """;
           String sq6 = """
                   UPDATE users
                   SET email = ?
                   WHERE uuid = ?
                   """;
           String sql7 = """
                   UPDATE users
                   SET password = ?
                   WHERE uuid = ?
                   """;
           //
           PreparedStatement pre = con.prepareStatement(sql1);
           pre.setString(1, userUpdateDto.userName());
           pre.setString(2, userUpdateDto.email());
           pre.setString(3, userUpdateDto.password());
           pre.setString(4, uuid);
           int rowAffected = pre.executeUpdate();
           return findByUserUuid(uuid);
       }catch (Exception exception){
           System.err.println("ERROR during update user by uuid: " + exception.getMessage());
       }
        return null;
    }
    public User findByUserId(Integer id){
        try(Connection con = DatabaseConfigure.getDatabaseConnection()){
            String sql = """
                    SELECT * FROM users
                    WHERE id = ?
                    """;
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet resultSet = pre.executeQuery();
            User user = new User();
            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUuid(resultSet.getString("uuid"));
                user.setUserName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setIsDeleted(resultSet.getBoolean("is_deleted"));
            }
            return user;
        }catch (Exception exception){
            System.err.println("ERROR during find user by id :" + exception.getMessage());
        }
        return null;
    }
}
