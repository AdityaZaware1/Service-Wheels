package com.ben.Booking.Service.external;

import com.ben.Booking.Service.dto.ShopDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SHOP-SERVICE")
public interface ShopService {

    @GetMapping("/get/{id}")
    public ShopDto getShop(@PathVariable Long id);
}
