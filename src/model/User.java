package model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class  User {
    private Integer id;
    private String uuid;
    private String userName;
    private String email;
    private String password;
    private String gender;
    private Boolean isVerified;
    private Boolean isDeleted;
    // association
    private Set<History> histories = new HashSet<>();
}
