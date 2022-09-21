package com.hackathlon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HackathlonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackathlonApplication.class, args);
	}

}
