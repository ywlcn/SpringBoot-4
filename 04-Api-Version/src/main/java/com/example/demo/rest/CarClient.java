package com.example.demo.rest;

import com.example.demo.dto.Car;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

// version  4.0 から
@HttpExchange(url = "/server") // , version = "1.1"
public interface CarClient {

    @GetExchange(value = "/car", version = "1.1")
    Car getCar11();

    @GetExchange(value = "/car", version = "1.2")
    Car getCar12();

    @GetExchange(value = "/car", accept = "application/json;v=1.1")
    Car getCar11Media();

    @GetExchange(value = "/car", accept = "application/json;v=1.2")
    Car getCar12Media();

}