package com.darjedaar.inventorytracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.darjedaar.inventorytracker.model"})
public class InventorytrackerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(InventorytrackerApplication.class, args);
	}
	
}
