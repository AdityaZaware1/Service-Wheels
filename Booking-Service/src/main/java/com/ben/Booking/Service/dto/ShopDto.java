package com.ben.Booking.Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {

    private Long id;

    private String name;

    private String ownerName;
    private String ownerEmail;
    private String ownerPhone;
    private String address;
    private String vehicleType;
    private BigDecimal ratings;
    private LocalTime openTime;
    private LocalTime closeTime;

}
