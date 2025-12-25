package model;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class History {
    private Integer id;
    private String uuid;
    private Date historyDate;
    //
    private Set<Book> books = new HashSet<>();
}
