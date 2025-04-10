package com.example.Services_Offering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServicesOfferingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesOfferingApplication.class, args);
	}

}
