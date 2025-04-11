package com.ben.external;

import com.ben.dto.BookingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "BOOKING-SERVICE")
public interface BookingService {

    @GetMapping("get/{id}/")
    public BookingDto getBooking(@PathVariable Long id);
}
