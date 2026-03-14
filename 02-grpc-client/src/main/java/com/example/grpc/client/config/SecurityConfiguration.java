package com.example.grpc.client.config;

import com.example.grpc.client.interceptor.MyBasicAuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.grpc.client.GlobalClientInterceptor;
import org.springframework.grpc.client.interceptor.security.BasicAuthenticationInterceptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    public final static String LOGIN_URL = "/login";
    public final static String HEADER_AUTH_KEY = "X-AUTH-TOKEN";

    public final static String[] PERMIT_ALL_URL = new String[]{LOGIN_URL};


    @Bean
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http , PasswordEncoder passwordEncoder) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );


        return http.build();
    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authorize) ->
//                        authorize.requestMatchers("/**").permitAll()
//                                .anyRequest().authenticated()
//
//                );
//        return http.build();
//    }


    @Bean
    @Order(0)
    @GlobalClientInterceptor
    BasicAuthenticationInterceptor basicAuthenticationInterceptor( ) {
        return new BasicAuthenticationInterceptor("user1", "password");
//        return new MyBasicAuthenticationInterceptor();
    }

}