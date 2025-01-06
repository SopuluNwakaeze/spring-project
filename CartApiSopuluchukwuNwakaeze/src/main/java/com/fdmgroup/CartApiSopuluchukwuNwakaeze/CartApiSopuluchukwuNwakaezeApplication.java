package com.fdmgroup.CartApiSopuluchukwuNwakaeze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient 
public class CartApiSopuluchukwuNwakaezeApplication {
	public static String CONTACT_API_ROOT = "http://localhost:8005";

	public static void main(String[] args) {
		SpringApplication.run(CartApiSopuluchukwuNwakaezeApplication.class, args);
	}

	@Bean(name = "webClient")
	public WebClient contactWebClient() {
		return WebClient.builder().baseUrl(CONTACT_API_ROOT)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

}
