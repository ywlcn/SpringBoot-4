package com.example.demo.config;

import com.example.demo.dto.Car;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiConfig implements WebMvcConfigurer {

    // Header より ないとNG
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.useRequestHeader("API-Version");
    }

//    @Override
//    public void configureApiVersioning(ApiVersionConfigurer configurer) {
//        configurer.usePathSegment(1);
//    }


//    // path より
//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer.addPathPrefix("/api/v{version}", HandlerTypePredicate.forAnnotation(RestController.class));
//    }
//    Car car = client.get().uri("/api/v1.1/server/car")
//            .apiVersion(1.1)
//            .retrieve()
//            .body(Car.class);

}