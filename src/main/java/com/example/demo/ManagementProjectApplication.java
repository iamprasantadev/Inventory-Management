package com.example.demo;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
public class ManagementProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementProjectApplication.class, args);
	}
	@Bean 
	public ModelMapper modelMapper() {
		 ModelMapper modelMapper = new ModelMapper();			
			  modelMapper.getConfiguration() 
			  .setFieldAccessLevel(AccessLevel.PRIVATE)
			  .setMatchingStrategy(MatchingStrategies.STANDARD) 
			  .setSkipNullEnabled(true);
			 
		 return modelMapper;

	   }
//	@Bean
//	@Primary
//	CorsConfigurationSource corsConfigurationSource() {
//	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    CorsConfiguration config = new CorsConfiguration();
//	    source.registerCorsConfiguration("/**", config.applyPermitDefaultValues());
//	    //allow Authorization to be exposed
//	    config.setExposedHeaders(Arrays.asList("Authorization"));
//        
//	    return source;
//	}
	
}
