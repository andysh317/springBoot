package com.org.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "com.org")
public class AuthService {
	 public static void main(String[] args) {
	        SpringApplication.run(AuthService.class, args);
	    }
}
