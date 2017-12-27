package com.epic951;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelecomPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelecomPlatformApplication.class, args);
	}

	// Enabling Global CORS support
	// @Bean
	// public WebMvcConfigurer corsConfigurer() {
	// return new WebMvcConfigurerAdapter() {
	// @Override
	// public void addCorsMappings(CorsRegistry registry) {
	// registry.addMapping("/**")
	// .allowedOrigins("http://localhost:4200", "http://localhost:5000",
	// "https://telecom-platform-frontend.herokuapp.com/")
	// .allowedMethods("OPTIONS", "GET", "PUT", "POST",
	// "DELETE").allowCredentials(true)
	// .allowedHeaders("*");
	// }
	// };
	// }

}
