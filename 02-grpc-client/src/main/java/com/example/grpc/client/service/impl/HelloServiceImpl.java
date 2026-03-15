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
        HelloRepositoryOutDto response = new HelloRepositoryOutDto();
        try{
            response = helloRepository.sayHello(repositoryInDto);
        }catch (Exception e){
            response.setMessage(e.getMessage());
        }

        return mapper.mapToDto(response);
    }

}
