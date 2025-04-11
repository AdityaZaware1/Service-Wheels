package com.ben.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicesDto {

    private Long id;
    private String name;
    private String description;
    private int price;
    private int duration;
}
