package com.example.demo.config;

import com.example.demo.dto.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ApiVersionInserter;
import org.springframework.web.client.RestClient;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.example.demo.config.ApiHeaderConfig.API_VERSION_HEADER_NAME;

@Configuration
@ConditionalOnProperty(prefix = "api", name = "type", havingValue = "PathSegment")
public class ApiPathSegmentConfig implements WebMvcConfigurer, CommandLineRunner {

    @Value("${server.port}")
    public String serverPort;

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.usePathSegment(1);

    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api/{version}", HandlerTypePredicate.forAnnotation(RestController.class));
    }


    @Override
    public void run(String... args) throws Exception {

        RestClient client = RestClient.builder()
                .baseUrl("http://localhost:" + serverPort)
                .apiVersionInserter(ApiVersionInserter.usePathSegment(1))
                .build();

        Car car = client.get().uri("/api/1.1/server/car")
                .retrieve()
                .body(Car.class);

        System.out.println("[PathSegment]return from server:" + car.getName());

        car = client.get().uri("/api/1.2/server/car")
                .retrieve()
                .body(Car.class);

        System.out.println("[PathSegment]return from server:" + car.getName());

    }


}