package com.trilogy.cloudgamestoreconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CloudGameStoreConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGameStoreConfigApplication.class, args);
	}

}
