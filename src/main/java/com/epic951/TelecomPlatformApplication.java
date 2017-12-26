package com.epic951;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class TelecomPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelecomPlatformApplication.class, args);
	}

	// Enabling Global CORS support
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*")
						.allowedOrigins("http://localhost:4200", "http://localhost:5000",
								"https://telecom-platform-frontend.herokuapp.com/")
						.allowCredentials(true).allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
			}
		};
	}
}
