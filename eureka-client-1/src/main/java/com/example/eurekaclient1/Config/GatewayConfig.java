package com.example.eurekaclient1.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("client-2", r->r.path("/client-2/**").uri("http://localhost:8082")) //static routing
                .route("client-3", r->r.path("/client-3/**").uri("lb://client-3")) //dynamic routing
                .build();
    }
}
