package com.example.Services_Offering.service.impl;

import com.example.Services_Offering.dto.ShopDto;
import com.example.Services_Offering.entity.Services;
import com.example.Services_Offering.external.ShopService;
import com.example.Services_Offering.repo.ServiceRepo;
import com.example.Services_Offering.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    private final ServiceRepo serviceRepo;
    private final ShopService shopService;

    @Override
    public Services createServiceOffering(Services services, Long shopId) {

        ShopDto shopDto = shopService.getShop(shopId);

        if (shopDto == null) {
            throw new RuntimeException("shop in not exist" + shopId);
        }

        Services newService = new Services();
        newService.setName(services.getName());
        newService.setDescription(services.getDescription());
        newService.setPrice(services.getPrice());
        newService.setDuration(services.getDuration());
        newService.setShopId(shopId);

        return serviceRepo.save(newService);
    }

    @Override
    public Services updateServiceOffering(Services services, Long id) {
        Services existing = getServiceById(id);

        if (existing == null) {
            throw new RuntimeException("service in not exist" + id);
        }

        existing.setName(services.getName());
        existing.setDescription(services.getDescription());
        existing.setPrice(services.getPrice());
        existing.setDuration(services.getDuration());

        return serviceRepo.save(existing);
    }

    @Override
    public List<Services> getAllServicesByShop(Long id) {
        List<Services> services = serviceRepo.findServicesByShopId(id);
        return services;
    }

    @Override
    public List<Services> getServicesById(List<Long> id) {
        return serviceRepo.findAllById(id);
    }

    @Override
    public Services getServiceById(Long id) {
        Services services =  serviceRepo.findById(id).orElse(null);

        if (services == null) {
            throw new RuntimeException("service in not exist" + id);
        }

        return services;
    }

    @Override
    public void deleteServiceOffering(Long serviceId) {
        Services services = getServiceById(serviceId);

        serviceRepo.delete(services);
    }
}
