package model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Integer id;
    private String uuid;
    private String title;
    private String description;
    private Set<String> imageUrls;
    // relationship (one to many)
    private Integer userId;
}
