package com.project.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	private String connectPath = "/uploadFile/**";
	
	// 운영체제에 따라 파일 저장경로 설정
	public String getOperatingSystem() {
	    String os = System.getProperty("os.name").toLowerCase();
	    System.out.println("Using System Property: " + os);
	    if(os.contains("win")) {
	    	return "file:///C:/uploadFile/";
	    }else {
	    	return "file:///Users/jihyeonjeong/uploadFile/";
	    }
	    
	}
	
	private String resourcePath = getOperatingSystem();
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(connectPath)
				.addResourceLocations(resourcePath);	
	}

}
