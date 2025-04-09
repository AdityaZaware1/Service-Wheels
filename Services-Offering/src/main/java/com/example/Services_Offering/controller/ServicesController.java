package com.example.Services_Offering.controller;

import com.example.Services_Offering.entity.Services;
import com.example.Services_Offering.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/services")
public class ServicesController {

    private final ServiceOfferingService service;


    @PostMapping("/create/{id}")
    public ResponseEntity<Services> createService(@RequestBody Services services, @PathVariable Long id) {

        return ResponseEntity.ok(service.createServiceOffering(services, id));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Services> getService(@PathVariable Long id) {

        return ResponseEntity.ok(service.getServiceById(id));
    }

    @GetMapping("get/{shopId}")
    public ResponseEntity<List<Services>> getServicesByShop(@PathVariable Long shopId) {

        return ResponseEntity.ok(service.getAllServicesByShop(shopId));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Services>> getServices(@RequestParam List<Long> id) {

        return ResponseEntity.ok(service.getServicesById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Services> updateService(@RequestBody Services services, @PathVariable Long id) {

        return ResponseEntity.ok(service.updateServiceOffering(services, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        service.deleteServiceOffering(id);
        return ResponseEntity.noContent().build();
    }
}
