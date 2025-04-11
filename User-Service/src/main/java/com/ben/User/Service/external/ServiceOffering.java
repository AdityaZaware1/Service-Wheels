package com.ben.User.Service.external;

import com.ben.User.Service.dto.ServicesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "SERVICES_OFFERING")
public interface ServiceOffering {

    @PostMapping("/create/{id}")
    public ServicesDto createService(@RequestBody ServicesDto services, @PathVariable Long id);
}
