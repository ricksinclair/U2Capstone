package com.trilogy.cloudgamestoreproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CloudGameStoreProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGameStoreProductServiceApplication.class, args);
	}

}
