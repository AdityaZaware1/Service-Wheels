package com.ben.Booking.Service.dto;

import lombok.Data;

@Data
public class ShopReport {

    private Long shopId;
    private String shopName;
    private Double totalEarnings;
    private Integer totalBookings;
    private Integer cancelledBookings;

}
