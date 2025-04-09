package com.ben.Booking.Service.entity;

import com.ben.Booking.Service.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long shopId;

    private Long userId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ElementCollection
    private List<Long> serviceIds;

    private BookingStatus status = BookingStatus.PENDING;

    private int totalCost;
}
