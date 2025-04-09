package com.ben.Booking.Service.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Long> serviceIds;
}
