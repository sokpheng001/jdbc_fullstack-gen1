package model;

import lombok.Data;

import java.util.Date;

@Data
public class Coupon {
    private Integer id;
    private String uuid;
    private String couponCode;
    private Date expireDate;
}
