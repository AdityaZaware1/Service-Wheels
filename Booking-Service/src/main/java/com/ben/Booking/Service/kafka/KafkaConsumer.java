package com.ben.Booking.Service.kafka;

import com.ben.Booking.Service.entity.Booking;
import com.ben.Booking.Service.enums.BookingStatus;
import com.ben.Booking.Service.repo.BookingRepo;
import com.ben.Booking.Service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final BookingService bookingService;

    @KafkaListener(topics = "payment_done", groupId = "booking-group")
    public void completeBooking(String id) {
        Booking booking = bookingService.getBookingById(Long.parseLong(id));

        booking.setStatus(BookingStatus.CONFIRMED);
        bookingService.updateBooking(booking.getId(), BookingStatus.CONFIRMED);
    }
}
