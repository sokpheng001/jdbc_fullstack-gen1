package model;

import lombok.Data;

import java.util.Date;
@Data
public class Book {
    private Integer id;
    private String uuid;
    private String bookingName;
    private Date bookedDate;
    private BookingStatus bookingStatus;
    // Vehicle
    private Vehicle vehicle;
}
