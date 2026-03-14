package com.example.grpc.client.repository.impl;

import com.example.proto.SampleProto;
import com.example.proto.SampleGrpc;
import com.example.grpc.client.repository.HelloRepository;
import com.example.grpc.client.repository.dto.HelloRepositoryInDto;
import com.example.grpc.client.repository.dto.HelloRepositoryOutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloRepositoryImpl implements HelloRepository {

    @Autowired
    SampleGrpc.SampleBlockingStub stub;

    @Override
    public HelloRepositoryOutDto sayHello(HelloRepositoryInDto inDto) {
        SampleProto.HelloRequest request = mapper.mapToProto(inDto);
        SampleProto.HelloReply response = stub.sayHello(request);
        return mapper.mapToDto(response);
    }

}
