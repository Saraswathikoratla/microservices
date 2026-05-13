package com.saraswathi.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class GatewayConfig {


    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                // ✅ User Service
                .route("user-service", r -> r
                        .path("/auth/**")
                        .uri("http://localhost:8081"))

                // ✅ Order Service
                .route("order-service", r -> r
                        .path("/orders/**")
                        .uri("http://localhost:8082"))
                .route("product-service", r -> r
                        .path("/products/**")
                        .uri("lb://PRODUCT-SERVICE"))

                .build();
    }
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)   // ✅ disable basic auth
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)   // ✅ disable login

                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/auth/**").permitAll()  // allow login
                        .anyExchange().permitAll()             // let JwtFilter handle security
                );

        return http.build();
    }
}