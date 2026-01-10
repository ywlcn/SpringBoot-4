package com.example.demo.controller;

import com.example.demo.dto.Car;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("server")
@RestController
public class RestServerController {

    @GetMapping(path = "car", version = "1.1")
    public Car getCar1() {
//        System.out.println("1.1 called");
        return new Car("1.1");
    }

    @GetMapping(path = "car", version = "1.2")
    public Car getCar2() {
//        System.out.println("1.2 called");
        return new Car("1.2");
    }

}
