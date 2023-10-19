package com.springboot.MySpringBoot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MySpringBootApplication {

	public static void main(String[] args) {

		SpringApplication.run(MySpringBootApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}
}
