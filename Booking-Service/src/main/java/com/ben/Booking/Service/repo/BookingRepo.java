package com.ben.Booking.Service.repo;

import com.ben.Booking.Service.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);

    List<Booking> findByShopId(Long shopId);
}
