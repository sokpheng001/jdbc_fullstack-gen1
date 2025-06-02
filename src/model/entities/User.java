package model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class User {
    private Integer id;
    private String uuid;
    private String userName;
    private String email;
    private String password;
    private Boolean isDeleted;
}
