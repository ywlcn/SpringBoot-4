package com.example.grpc.security;

import com.example.grpc.core.entity.UserEntity;
import com.example.grpc.core.repository.UserRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@MapperScan("com.example.grpc.core.repository.mapper")
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

    /// /                .password(passwordEncoder.encode("password"))
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
    @Bean
    public InMemoryUserDetailsManager userDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        List<UserEntity> users = userRepository.findAll();


        List<UserDetails> userDetails = new ArrayList<>();


        users.forEach( u -> {
            UserDetails user = User.builder()
                    .username(u.getName())
                    .password(passwordEncoder.encode(u.getPassword()))
                    .roles(u.getRoles().split(","))
                    .build();
            userDetails.add(user);
        });

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

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(userDetails);
//        manager.setAuthenticationManager();

        return manager;
    }

}
