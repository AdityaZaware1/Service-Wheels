package com.ben.Booking.Service.dto;

import jakarta.persistence.Column;
import jakarta.persistence.NamedEntityGraph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {

    private Long id;
    private String name;
    private String description;
    private Long shopId;
    private int price;
    private int duration;
}
