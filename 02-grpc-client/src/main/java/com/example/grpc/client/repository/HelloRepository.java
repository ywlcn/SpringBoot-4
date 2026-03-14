package com.example.grpc.client.repository;

import com.example.grpc.client.mapper.HelloMapper;
import com.example.grpc.client.repository.dto.HelloRepositoryInDto;
import com.example.grpc.client.repository.dto.HelloRepositoryOutDto;

public interface HelloRepository {

    HelloMapper mapper = HelloMapper.INSTANCE;

    HelloRepositoryOutDto sayHello(HelloRepositoryInDto inDto);

}
