package com.trilogy.cloudgamestoreeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CloudGameStoreEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGameStoreEurekaApplication.class, args);
	}

}
