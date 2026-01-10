package com.example.demo.config;

import com.example.demo.dto.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ApiVersionInserter;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnProperty(prefix = "api", name = "type", havingValue = "Header")
public class ApiHeaderConfig implements WebMvcConfigurer, CommandLineRunner {

    public static final String API_VERSION_HEADER_NAME = "API-Version";

    @Value("${server.port}")
    public String serverPort;

    // Header より ないとNG
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.useRequestHeader("API-Version");
    }

    @Override
    public void run(String... args) throws Exception {

        RestClient client = RestClient.builder()
                .baseUrl("http://localhost:" + serverPort)
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

        // header設定しないと通信エラーになります。
        // car = client.get().uri("/server/car")
        //        .retrieve()
        //        .body(Car.class);
        // System.out.println("return from server:" + car.getName());

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