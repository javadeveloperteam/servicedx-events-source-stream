package com.servicedx.source.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EventsSourceStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsSourceStreamApplication.class, args);
	}

}
