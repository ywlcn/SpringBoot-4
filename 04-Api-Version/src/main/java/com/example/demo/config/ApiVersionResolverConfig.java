package com.example.demo.config;

import com.example.demo.dto.Car;
import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.ApiVersionResolver;
import org.springframework.web.client.ApiVersionInserter;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnProperty(prefix = "api", name = "type", havingValue = "VersionResolver")
public class ApiVersionResolverConfig implements WebMvcConfigurer , CommandLineRunner {

    public static final String API_VERSION_HEADER_NAME = "API-Version";

    // Header
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.useVersionResolver(new MyApiVersionResolver(API_VERSION_HEADER_NAME));
    }


    @Override
    public void run(String... args) throws Exception {

        RestClient client = RestClient.builder()
                .baseUrl("http://localhost:8001")
                .apiVersionInserter(ApiVersionInserter.useHeader(API_VERSION_HEADER_NAME))
                .build();

        Car car = client.get().uri("/server/car")
                .apiVersion(1.1)
                .retrieve()
                .body(Car.class);

        System.out.println("return from server:" + car.getName());

        car = client.get().uri("/server/car")
                .apiVersion(1.2)
                .retrieve()
                .body(Car.class);

        System.out.println("return from server:" + car.getName());

    }

    public class MyApiVersionResolver implements ApiVersionResolver {

        private final String headerName;

        public MyApiVersionResolver(String headerName) {
            this.headerName = headerName;
        }


        @Override
        public @Nullable String resolveVersion(HttpServletRequest request) {
            return request.getHeader(this.headerName);
        }

    }

}