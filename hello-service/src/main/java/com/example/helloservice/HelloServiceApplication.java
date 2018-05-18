package com.example.helloservice;

import com.example.helloservice.serverportconfig.MyApplicationEnvironmentPreparedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HelloServiceApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(HelloServiceApplication.class);
		app.addListeners(new MyApplicationEnvironmentPreparedEventListener());
		app.run(args);

		//SpringApplication.run(HelloServiceApplication.class, args);
	}
}
