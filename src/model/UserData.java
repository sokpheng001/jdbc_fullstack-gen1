package model;

import model.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserData {
    public static List<User> userList
             = new ArrayList<>(
                     List.of(new User(1, UUID.randomUUID().toString(),
                             "koko","koko123@gmail.com"
                     ,"!@!#!@$#%$^$%", false))
    );
}
