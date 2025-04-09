package com.ben.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private Long id;

    private Long shopId;

    private Long userId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ElementCollection
    private List<Long> serviceIds;

    private int totalCost;
}
