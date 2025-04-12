package com.ben.Booking.Service.controller;

import com.ben.Booking.Service.dto.ServiceDto;
import com.ben.Booking.Service.dto.ShopDto;
import com.ben.Booking.Service.dto.ShopReport;
import com.ben.Booking.Service.entity.Booking;
import com.ben.Booking.Service.enums.BookingStatus;
import com.ben.Booking.Service.request.BookingRequest;
import com.ben.Booking.Service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("create/{id}/")
    public ResponseEntity<Booking> createBooking(
            @RequestParam Long id,
            @RequestBody BookingRequest bookingRequest,
            @RequestParam Long shopId,
            @RequestBody List<Long> serviceDtos
    ) {

        return ResponseEntity.ok(bookingService.createBooking(bookingRequest, id, shopId, serviceDtos));
    }

    @GetMapping("get/{id}/")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @PutMapping("update/{id}/")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody BookingStatus status) {
        return ResponseEntity.ok(bookingService.updateBooking(id, status));
    }

    @GetMapping("user/{id}/")
    public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingsByUser(id));
    }

    @GetMapping("shop/{id}/")
    public ResponseEntity<List<Booking>> getBookingsByShop(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingsByShop(id));
    }

    @GetMapping("date/{date}/{id}/")
    public ResponseEntity<List<Booking>> getBookingsByDate(@PathVariable LocalDate date, @PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingsByDate(date, id));
    }

    @GetMapping("report/{id}/")
    public ResponseEntity<ShopReport> getShopReport(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getShopReport(id));
    }

}
