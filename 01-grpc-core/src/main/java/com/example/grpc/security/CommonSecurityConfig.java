package com.example.grpc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class CommonSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


//    @Bean
//    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
//        // テストユーザー1: 一般ユーザー
//        UserDetails user = User.builder()
//                .username("user1")
////                .password(passwordEncoder.encode("password"))
//                .password(passwordEncoder.encode("password"))
//                .roles("USER")
//                .build();
//
//        // テストユーザー2: 管理者
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin123"))
//                .roles("ADMIN")
//                .build();
//
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user , admin);
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(inMemoryUserDetailsManager);
//        authenticationProvider.setUserDetailsPasswordService(inMemoryUserDetailsManager);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//
//        ProviderManager providerManager = new ProviderManager(authenticationProvider);
//        providerManager.setEraseCredentialsAfterAuthentication(false);
//
//        return providerManager;
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//
//        String password = passwordEncoder.encode("password");
//
//        // テストユーザー1: 一般ユーザー
//        UserDetails user = User.builder()
//                .username("user1")
////                .password(passwordEncoder.encode("password"))
//                .password(passwordEncoder.encode("password"))
//                .roles("USER")
//                .build();
//
//        // テストユーザー2: 管理者
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin123"))
//                .roles("ADMIN")
//                .build();
//
//        InMemoryUserDetailsManager manager =  new InMemoryUserDetailsManager(user, admin);
//        manager.setAuthenticationManager();
//
//        return manager;
//    }

}
