package com.example.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-service", r -> r.path("/users/**")
                .filters(f -> f.filter(jwtAuthenticationFilter))
                .uri("lb://user-service"))
            .route("order-service", r -> r.path("/orders/**") // ✅ fixed from /order/** to /orders/**
                .filters(f -> f.filter(jwtAuthenticationFilter))
                .uri("lb://order-service"))
            .route("auth-service", r -> r.path("/auth/**") // still public
                .uri("lb://auth-service"))
            .build();
    }
}
