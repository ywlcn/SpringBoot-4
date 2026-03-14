package com.example.grpc.server.controller;

import com.example.proto.SampleProto;
import com.example.proto.SampleGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

@GrpcService
@RequiredArgsConstructor
public class GrpcServerService extends SampleGrpc.SampleImplBase {

    private static final Logger log = LoggerFactory.getLogger(GrpcServerService.class);

    @Override
    public void sayHello(SampleProto.HelloRequest req, StreamObserver<SampleProto.HelloReply> responseObserver) {
        log.info("Hello " + req.getName());
        if (req.getName().startsWith("error")) {
            throw new IllegalArgumentException("Bad name: " + req.getName());
        }
        if (req.getName().startsWith("internal")) {
            throw new RuntimeException();
        }
        SampleProto.HelloReply reply = SampleProto.HelloReply.newBuilder().setMessage("Hello ==> " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }



    @Override
    public void streamHello(SampleProto.HelloRequest req, StreamObserver<SampleProto.HelloReply> responseObserver) {
        log.info("Hello " + req.getName());
        int count = 0;
        while (count < 10) {
            SampleProto.HelloReply reply = SampleProto.HelloReply.newBuilder().setMessage("Hello(" + count + ") ==> " + req.getName()).build();
            responseObserver.onNext(reply);
            count++;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                responseObserver.onError(e);
                return;
            }
        }
        responseObserver.onCompleted();
    }
}