package com.ben.Booking.Service.service;


import com.ben.Booking.Service.dto.ServiceDto;
import com.ben.Booking.Service.dto.ShopDto;
import com.ben.Booking.Service.dto.ShopReport;
import com.ben.Booking.Service.entity.Booking;
import com.ben.Booking.Service.enums.BookingStatus;
import com.ben.Booking.Service.request.BookingRequest;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    Booking createBooking(BookingRequest booking, Long userOd, ShopDto shopId, List<ServiceDto> serviceDtos);

    List<Booking> getBookingsByUser(Long userId);

    List<Booking> getBookingsByShop(Long shopId);

    Booking getBookingById(Long id);

    Booking updateBooking(Long id, BookingStatus status);

    List<Booking> getBookingsByDate(LocalDate date, Long shopId);

    ShopReport getShopReport(Long shopId);
}
