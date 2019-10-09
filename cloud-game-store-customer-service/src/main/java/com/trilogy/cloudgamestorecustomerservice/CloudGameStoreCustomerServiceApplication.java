package com.trilogy.cloudgamestorecustomerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudGameStoreCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGameStoreCustomerServiceApplication.class, args);
	}

}
