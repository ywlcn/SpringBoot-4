package com.example.demo;

import com.example.demo.config.MyBeanRegistrar;
import com.example.demo.dto.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import(MyBeanRegistrar.class)
public class ApiVersionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiVersionApplication.class, args);
	}

	@Bean
	Car car(){
		return new Car("@Bean");
	}

}
