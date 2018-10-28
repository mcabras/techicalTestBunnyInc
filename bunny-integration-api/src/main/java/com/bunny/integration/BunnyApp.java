package com.bunny.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.integration.annotation.IntegrationComponentScan;

@IntegrationComponentScan
@EnableCaching
@SpringBootApplication
public class BunnyApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BunnyApp.class, args);
	}

}
