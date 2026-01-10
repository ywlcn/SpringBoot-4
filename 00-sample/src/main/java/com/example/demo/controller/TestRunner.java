package com.example.demo.controller;

import com.example.demo.dto.Car;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ApiVersionInserter;
import org.springframework.web.client.RestClient;

@Component
public class TestRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {


        RestClient client = RestClient.builder()
                .baseUrl("http://localhost:8001")
                .apiVersionInserter(ApiVersionInserter.useHeader("API-Version"))
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
