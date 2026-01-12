package com.example.demo.config;

import com.example.demo.dto.Car;
import com.example.demo.rest.CarClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ApiVersionInserter;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnProperty(prefix = "api", name = "type", havingValue = "QueryParam")
public class ApiQueryParamConfig implements WebMvcConfigurer, CommandLineRunner {

    public static final String API_VERSION_QUERY_PARAM_NAME = "version";

    @Value("${server.port}")
    public String serverPort;

    // Header より ないとNG
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.useQueryParam(API_VERSION_QUERY_PARAM_NAME);
    }

    @Override
    public void run(String... args) throws Exception {

        RestClient client = RestClient.builder()
                .baseUrl("http://localhost:" + serverPort)
                .apiVersionInserter(ApiVersionInserter.useQueryParam(API_VERSION_QUERY_PARAM_NAME))
                .build();

        Car car1 = client.get().uri("/server/car")
                .apiVersion(1.1)
                .retrieve()
                .body(Car.class);
        Car car2 = client.get().uri("/server/car")
                .apiVersion(1.2)
                .retrieve()
                .body(Car.class);

        System.out.println("[QueryParam]return from server:" + car1.getName());
        System.out.println("[QueryParam]return from server:" + car2.getName());

        // HttpExchange
        CarClient carClient = carClient();
        car1 = carClient.getCar11();
        car2 = carClient.getCar12();

        System.out.println("[QueryParam](HttpExchange)return from server:" + car1.getName());
        System.out.println("[QueryParam](HttpExchange)return from server:" + car2.getName());

    }


    @Bean
    public CarClient carClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:" + serverPort)
                .apiVersionInserter(ApiVersionInserter.useQueryParam(API_VERSION_QUERY_PARAM_NAME))
                .build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(CarClient.class);
    }
}