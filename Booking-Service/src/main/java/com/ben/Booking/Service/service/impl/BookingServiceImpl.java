package com.ben.Booking.Service.service.impl;

import com.ben.Booking.Service.dto.ServiceDto;
import com.ben.Booking.Service.dto.ShopDto;
import com.ben.Booking.Service.dto.ShopReport;
import com.ben.Booking.Service.entity.Booking;
import com.ben.Booking.Service.enums.BookingStatus;
import com.ben.Booking.Service.external.AvailableService;
import com.ben.Booking.Service.external.ShopService;
import com.ben.Booking.Service.repo.BookingRepo;
import com.ben.Booking.Service.request.BookingRequest;
import com.ben.Booking.Service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepo bookingRepo;
    private final AvailableService availableService;
    private final ShopService shopService;

    @Override
    public Booking createBooking(BookingRequest booking,
                                 Long userOd, Long shopId, List<ServiceDto> serviceDtos) {

        int totalDuration = serviceDtos.stream()
                .mapToInt(ServiceDto::getDuration)
                .sum();

        LocalDateTime startTime = booking.getStartTime();
        LocalDateTime endTime = startTime.plusMinutes(totalDuration);

        ShopDto shop = shopService.getShop(shopId);

        Boolean isSlotAvailable = isTimeSlotAvailable(shop, startTime, endTime);

        if(!isSlotAvailable) {
            throw new RuntimeException("Slot is not available");
        }

        int totalPrice = serviceDtos.stream()
                .mapToInt(ServiceDto::getPrice)
                .sum();

        for(ServiceDto serviceDto : serviceDtos) {
            ServiceDto service = availableService.getService(serviceDto.getId());

            if(service == null) {
                throw new RuntimeException("Service not found");
            }
            List<Long> serviceIds = serviceDtos.stream()
                    .map(ServiceDto::getId)
                    .toList();
        }

        List<Long> serviceIds = serviceDtos.stream()
                .map(ServiceDto::getId)
                .toList();

        Booking newBooking = new Booking();

        newBooking.setShopId(shop.getId());
        newBooking.setUserId(userOd);
        newBooking.setStartTime(startTime);
        newBooking.setEndTime(endTime);
        newBooking.setServiceIds(serviceIds);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setTotalCost(totalPrice);

        return bookingRepo.save(newBooking);
    }

    @Override
    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepo.findByUserId(userId);
    }

    @Override
    public List<Booking> getBookingsByShop(Long shopId) {
        return bookingRepo.findByShopId(shopId);
    }

    @Override
    public Booking getBookingById(Long id) {
        Booking booking = bookingRepo.findById(id).orElse(null);

        if (booking == null) {
            throw new RuntimeException("Booking not found");
        }

        return booking;
    }

    @Override
    public Booking updateBooking(Long id, BookingStatus status) {
        Booking booking = getBookingById(id);
        booking.setStatus(status);
        return bookingRepo.save(booking);
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, Long shopId) {
        List<Booking> bookings = getBookingsByShop(shopId);

        if(date == null) {
            return bookings;
        }

        return bookings.stream()
                .filter(booking -> isSameDate(booking.getStartTime(), date) ||
                        isSameDate(booking.getEndTime(), date))
                .collect(Collectors.toList());
    }

    @Override
    public ShopReport getShopReport(Long shopId) {
        List<Booking> bookings = getBookingsByShop(shopId);

        Double totalEarnings = bookings.stream()
                .mapToDouble(Booking::getTotalCost)
                .sum();

        List<Booking> cancelledBookings = bookings.stream()
                .filter(booking -> booking.getStatus().equals(BookingStatus.CANCELLED))
                .collect(Collectors.toList());

        Double totalRefunds = cancelledBookings.stream()
                .mapToDouble(Booking::getTotalCost)
                .sum();

        ShopReport shopReport = new ShopReport();

        shopReport.setShopId(shopId);
        shopReport.setTotalEarnings(totalEarnings);
        shopReport.setTotalBookings(bookings.size());
        shopReport.setCancelledBookings(cancelledBookings.size());

        return shopReport;
    }


    public Boolean isTimeSlotAvailable(ShopDto shopDto,
                                       LocalDateTime startTime,
                                       LocalDateTime endTime) {

        List<Booking> existingBookings = getBookingsByShop(shopDto.getId());

        LocalDateTime shopOpenTime = shopDto.getOpenTime().atDate(startTime.toLocalDate());
        LocalDateTime salonCloseTime = shopDto.getCloseTime().atDate(endTime.toLocalDate());

        if(startTime.isBefore(shopOpenTime) || endTime.isAfter(salonCloseTime)) {
            return false;
        }

        for (Booking booking : existingBookings) {
            if(startTime.isAfter(booking.getStartTime()) && startTime.isBefore(booking.getEndTime())) {
                return false;
            }
            if(endTime.isAfter(booking.getStartTime()) && endTime.isBefore(booking.getEndTime())) {
                return false;
            }
        }

        return true;
    }

    private boolean isSameDate(LocalDateTime dateTime, LocalDate date) {
        return dateTime.toLocalDate().isEqual(date);
    }
}
