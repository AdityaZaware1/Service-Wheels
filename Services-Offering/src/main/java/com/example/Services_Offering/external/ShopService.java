package com.example.Services_Offering.external;

import com.example.Services_Offering.dto.ShopDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SHOP-SERVICE")
public interface ShopService {

    @GetMapping("/get/{id}")
    public ShopDto getShop(@PathVariable Long id);
}
