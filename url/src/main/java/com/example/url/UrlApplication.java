package com.example.url;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {com.example.url.User.User.class,com.example.url.Controller.Controller.class})
@EnableAutoConfiguration
public class UrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlApplication.class, args);
	}

}
