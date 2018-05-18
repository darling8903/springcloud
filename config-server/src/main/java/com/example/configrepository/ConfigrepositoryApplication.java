package com.example.configrepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigrepositoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigrepositoryApplication.class, args);
	}
}
