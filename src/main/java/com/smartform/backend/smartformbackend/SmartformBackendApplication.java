package com.smartform.backend.smartformbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartformBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartformBackendApplication.class, args);
	}

}
