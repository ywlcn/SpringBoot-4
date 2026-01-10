package com.example.repository.impl;

import com.example.mapper.HelloMapper;
import com.example.proto.HelloWorldProto;
import com.example.proto.SimpleGrpc;
import com.example.repository.HelloRepository;
import com.example.repository.dto.HelloRepositoryInDto;
import com.example.repository.dto.HelloRepositoryOutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloRepositoryImpl implements HelloRepository {

    @Autowired
    SimpleGrpc.SimpleBlockingStub stub;

    @Override
    public HelloRepositoryOutDto sayHello(HelloRepositoryInDto inDto) {
        HelloWorldProto.HelloRequest request = mapper.mapToProto(inDto);
        HelloWorldProto.HelloReply response = stub.sayHello(request);
        return mapper.mapToDto(response);
    }

}
