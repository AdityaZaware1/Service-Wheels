package com.example.Services_Offering.repo;

import com.example.Services_Offering.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepo extends JpaRepository<Services, Long> {
    List<Services> findServicesByShopId(Long shopId);
}
