package com.project;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication(exclude={MultipartAutoConfiguration.class})
// @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ProjectApplication {
	
	public static void main(String[] args) {
		System.out.println("런 실행 완료");
		SpringApplication.run(ProjectApplication.class, args);
		
	}
}