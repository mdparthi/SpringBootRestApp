package com.qht.boot.rest;

import org.qht.client.RestClientController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RestClientController.class)
public class SpringBootRestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestAppApplication.class, args);
	}
}
