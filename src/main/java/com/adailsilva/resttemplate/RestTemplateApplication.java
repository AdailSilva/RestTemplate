package com.adailsilva.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adailsilva.resttemplate.configurations.RestTemplateConfig;

@SpringBootApplication
public class RestTemplateApplication {
	
	@Autowired
	RestTemplateConfig restTemplateConfig;

	public static void main(String[] args) {
		SpringApplication.run(RestTemplateApplication.class, args);
	}

}
