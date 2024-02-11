package com.pohostinstvo.pohostinstvo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PohostinstvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PohostinstvoApplication.class, args);
	}

}
