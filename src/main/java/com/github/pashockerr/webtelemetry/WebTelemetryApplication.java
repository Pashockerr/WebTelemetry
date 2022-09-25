package com.github.pashockerr.webtelemetry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebTelemetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebTelemetryApplication.class, args);
	}

}
