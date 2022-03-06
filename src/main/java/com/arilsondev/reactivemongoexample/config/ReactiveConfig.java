package com.arilsondev.reactivemongoexample.config;

import com.arilsondev.reactivemongoexample.service.RouterHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class ReactiveConfig {

    @Bean
    RouterFunction<?> routerFunction(RouterHandlers routerHandlers) {

        return RouterFunctions.route(
                RequestPredicates.GET("/rest/employees/all"),
                        routerHandlers::getAll        )
                .andRoute(RequestPredicates.GET("/rest/employees/{id}"),
                        routerHandlers::getId)
                .andRoute(RequestPredicates.GET("/rest/employees/{id}/events"), routerHandlers::getEvents)
                ;
    }

}
