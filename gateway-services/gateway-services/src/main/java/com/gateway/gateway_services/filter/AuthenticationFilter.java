package com.gateway.gateway_services.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import com.gateway.gateway_services.config.RouterValidator;
import com.gateway.gateway_services.dto.UserDto;

import reactor.core.publisher.Mono;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    private final RouterValidator routerValidator;
    private final WebClient.Builder webClientBuilder;

    public String token;

    @Autowired
    public AuthenticationFilter(RouterValidator routerValidator, WebClient.Builder webClientBuilder) {
        this.routerValidator = routerValidator;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (routerValidator.isSecured.test(request)) {
            return getTokenFromAuthService()
                .flatMap(token -> {
                    return chain.filter(exchange);
                })
                .onErrorResume(e -> this.onError(exchange, HttpStatus.UNAUTHORIZED));
        }
        return chain.filter(exchange);
    }

    private Mono<String> getTokenFromAuthService() {
        return webClientBuilder.build()
            .post()
            .uri("http://AUTH-SERVICES/auth/login")
            .retrieve()
            .bodyToMono(UserDto.class)
            .flatMap(response -> {
                if ("Login failed".equals(response.toString())) {
                    return Mono.error(new RuntimeException("Login failed"));
                }

                token = response.toString();
                return Mono.just(response.toString());
            });
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
}
