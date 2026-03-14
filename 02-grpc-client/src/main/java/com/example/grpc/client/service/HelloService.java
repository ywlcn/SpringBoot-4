package com.example.grpc.client.service;

import com.example.grpc.client.mapper.HelloMapper;
import com.example.grpc.client.service.dto.HelloServiceInDto;
import com.example.grpc.client.service.dto.HelloServiceOutDto;

public interface HelloService {

    HelloMapper mapper = HelloMapper.INSTANCE;

    HelloServiceOutDto sayHello(HelloServiceInDto inDto);


}
