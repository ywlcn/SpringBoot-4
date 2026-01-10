package com.example.demo;

import com.example.demo.config.MyBeanRegistrar;
import com.example.demo.dto.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MyBeanRegistrar.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	Car car(){
		return new Car("@Bean");
	}

}
