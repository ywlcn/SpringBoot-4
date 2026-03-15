package com.example.grpc.client.config;

import com.example.grpc.client.interceptor.MyBasicAuthenticationInterceptor;
import com.example.grpc.core.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.grpc.client.GlobalClientInterceptor;
import org.springframework.grpc.client.interceptor.security.BasicAuthenticationInterceptor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class GrpcClientSecurityConfiguration {

    @Bean
    @Order(0)
    @GlobalClientInterceptor
    MyBasicAuthenticationInterceptor basicAuthenticationInterceptor(UserRepository userRepository) {
//        return new BasicAuthenticationInterceptor("user1", "password");
        return new MyBasicAuthenticationInterceptor(userRepository);
    }

}