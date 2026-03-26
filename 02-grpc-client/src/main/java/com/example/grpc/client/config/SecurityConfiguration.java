package com.example.grpc.client.config;

import com.example.grpc.client.config.internal.JwtAuthenticationFilter;
import com.example.grpc.client.config.internal.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    public final static String LOGIN_URL = "/login";
    public final static String HEADER_AUTH_KEY = "X-AUTH-TOKEN";

    public final static String[] PERMIT_ALL_URL = new String[]{LOGIN_URL};

    private final JwtTokenProvider tokenProvider;
    private final UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .csrf(csrf -> csrf.disable()) // APIやCookie管理の場合は要検討
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**" , "/download").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider, userDetailsService),
                        UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler((request, response, authentication) -> {
                            // ログイン成功時にJWTを生成してCookieにセット
                            String token = tokenProvider.generateToken(authentication.getName());
                            Cookie cookie = new Cookie("AUTH-TOKEN", token);
                            cookie.setHttpOnly(true); // JavaScriptからのアクセスを禁止（重要）
                            cookie.setSecure(false);  // 開発環境。本番(HTTPS)はtrue
                            cookie.setPath("/");
                            cookie.setMaxAge(86400);
                            response.addCookie(cookie);
                            response.sendRedirect("/home");
                        })
                );
        return http.build();
    }



//    @Bean
//    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http , PasswordEncoder passwordEncoder) throws Exception {
//
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/login", "/css/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/home", true)
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login?logout")
//                        .permitAll()
//                );
//
//
//        return http.build();
//    }


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



}