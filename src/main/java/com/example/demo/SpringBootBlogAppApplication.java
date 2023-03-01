package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootBlogAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBlogAppApplication.class, args);
	}

	@Bean public ModelMapper getMapper() {
		ModelMapper mapper = new ModelMapper();
		return mapper;
	}
}
