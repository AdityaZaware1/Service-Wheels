package com.example.Services_Offering.service;

import com.example.Services_Offering.dto.ShopDto;
import com.example.Services_Offering.entity.Services;

import java.security.Provider;
import java.util.List;

public interface ServiceOfferingService {

    Services createServiceOffering(Services services, Long shopId);

    Services updateServiceOffering(Services services, Long id);

    List<Services> getAllServicesByShop(Long id);

    List<Services> getServicesById(List<Long> id);

    Services getServiceById(Long id);

    void deleteServiceOffering(Long serviceId);


}
