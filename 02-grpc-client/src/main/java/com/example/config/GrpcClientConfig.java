package com.example.config;

import com.example.proto.SimpleGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class GrpcClientConfig {

//    @Bean
//    SimpleGrpc.SimpleBlockingStub stub(GrpcChannelFactory channels) {
//        return SimpleGrpc.newBlockingStub(channels.createChannel("0.0.0.0:9090"));
//    }

    @Bean
    SimpleGrpc.SimpleBlockingStub stub(GrpcChannelFactory channels) {
        return SimpleGrpc.newBlockingStub(channels.createChannel("local"));
    }

}
