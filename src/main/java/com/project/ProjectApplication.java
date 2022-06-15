package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication(exclude={MultipartAutoConfiguration.class})
// @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ProjectApplication {
	
	public static void main(String[] args) {
		System.out.println("실행 하는가?");
		SpringApplication.run(ProjectApplication.class, args);
		
	}
}