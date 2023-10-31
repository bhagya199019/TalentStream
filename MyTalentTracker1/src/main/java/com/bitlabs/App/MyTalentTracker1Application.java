package com.bitlabs.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
public class MyTalentTracker1Application {

	public static void main(String[] args) {
		SpringApplication.run(MyTalentTracker1Application.class, args);
	
	}

}
