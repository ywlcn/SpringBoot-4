package com.example.demo.config;

import com.example.demo.dto.Car;
import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.core.env.Environment;

public class MyBeanRegistrar implements BeanRegistrar {

    public MyBeanRegistrar() {

    }

    @Override
    public void register(BeanRegistry registry, Environment env) {
        System.out.println("register");
        registry.registerBean("car", Car.class, spec -> spec
                .supplier(context -> new Car("Hello World!")));

    }
}