package com.github.pashockerr.webtelemetry;

import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebTelemetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebTelemetryApplication.class, args);
	}

	@Bean
	public OkHttpClient okHttpClient(){
		return new OkHttpClient();
	}
}
