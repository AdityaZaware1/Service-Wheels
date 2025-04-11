package com.ben.Booking.Service.external;

import com.ben.Booking.Service.dto.ServiceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "SERVICE-OFFERING")
public interface AvailableService {

    @GetMapping("get/{shopId}")
    public List<ServiceDto> getServicesByShop(@PathVariable Long shopId);

    @GetMapping("/get/{id}")
    public ServiceDto getService(@PathVariable Long id);


}
