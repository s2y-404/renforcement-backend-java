package com.student_services.student_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StudentServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentServicesApplication.class, args);
	}

}
