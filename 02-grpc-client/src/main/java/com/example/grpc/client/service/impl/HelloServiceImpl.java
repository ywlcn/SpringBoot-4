package com.example.grpc.client.service.impl;

import com.example.grpc.client.repository.HelloRepository;
import com.example.grpc.client.repository.dto.HelloRepositoryInDto;
import com.example.grpc.client.repository.dto.HelloRepositoryOutDto;
import com.example.grpc.client.service.HelloService;
import com.example.grpc.client.service.dto.HelloServiceInDto;
import com.example.grpc.client.service.dto.HelloServiceOutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {

    @Autowired
    HelloRepository helloRepository;

    @Override
    public HelloServiceOutDto sayHello(HelloServiceInDto inDto) {
        HelloRepositoryInDto repositoryInDto = mapper.mapToDto(inDto);

        HelloRepositoryOutDto response = helloRepository.sayHello(repositoryInDto);
        return mapper.mapToDto(response);
    }

}
