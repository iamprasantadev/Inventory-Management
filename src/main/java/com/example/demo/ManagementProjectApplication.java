package com.example.demo;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
public class ManagementProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementProjectApplication.class, args);
	}
	@Bean 
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	   }
	@Bean
	@Primary
	CorsConfigurationSource corsConfigurationSource() {

	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    source.registerCorsConfiguration("/**", config.applyPermitDefaultValues());
	    //allow Authorization to be exposed
	    config.setExposedHeaders(Arrays.asList("Authorization"));

	    return source;
	}

}
