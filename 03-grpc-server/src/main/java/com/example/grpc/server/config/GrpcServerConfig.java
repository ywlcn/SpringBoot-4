package com.example.grpc.server.config;

import com.example.grpc.server.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.grpc.server.GlobalServerInterceptor;

@Configuration
public class GrpcServerConfig {

    @Bean
    @GlobalServerInterceptor
    @Order(0)
    LoggingInterceptor loggingInterceptor(){
        return new LoggingInterceptor();
    }
}
