package com.gateway.gateway_services.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gateway.gateway_services.filter.AuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("school-services", r -> r.path("/school-services/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://school-services"))

                .route("student-services", r -> r.path("/student-services/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://student-services"))

                .route("auth-services", r -> r.path("/auth-services/**")
                        .uri("lb://auth-services"))
                .build();
    }

}
