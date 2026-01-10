package com.example.demo.config;

import com.example.demo.dto.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.MediaTypeParamApiVersionResolver;
import org.springframework.web.client.ApiVersionInserter;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnProperty(prefix = "api", name = "type", havingValue = "MediaTypeParameter")
public class ApiMediaTypeParameterConfig implements WebMvcConfigurer , CommandLineRunner {

    public static final String API_VERSION_MEDIA_TYPE_NAME = "API-Version";


    @Value("${server.port}")
    public String serverPort;

    // Header より ないとNG
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.useMediaTypeParameter(MediaType.APPLICATION_JSON , API_VERSION_MEDIA_TYPE_NAME);
//        public ApiVersionConfigurer useMediaTypeParameter(MediaType compatibleMediaType, String paramName) {
//            this.versionResolvers.add(new MediaTypeParamApiVersionResolver(compatibleMediaType, paramName));
//            return this;
//        }
    }


    @Override
    public void run(String... args) throws Exception {

        RestClient client = RestClient.builder()
                .baseUrl("http://localhost:" + serverPort)
                .apiVersionInserter(ApiVersionInserter.useMediaTypeParam(API_VERSION_MEDIA_TYPE_NAME))
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

}