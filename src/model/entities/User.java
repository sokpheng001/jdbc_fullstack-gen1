package model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class User {
    private Integer id;
    private String uuid;
    private String userName;
    private String email;
    private String password;
    private Boolean isDeleted;
}
