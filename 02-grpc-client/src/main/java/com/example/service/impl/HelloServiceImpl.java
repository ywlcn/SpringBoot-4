package com.example.service.impl;

import com.example.repository.HelloRepository;
import com.example.repository.dto.HelloRepositoryInDto;
import com.example.repository.dto.HelloRepositoryOutDto;
import com.example.service.HelloService;
import com.example.service.dto.HelloServiceInDto;
import com.example.service.dto.HelloServiceOutDto;
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
