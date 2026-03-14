package com.example.grpc.server.interceptor;


import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class LoggingInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next) {

        long startTime = System.nanoTime();
        MethodDescriptor<ReqT, RespT> methodDescriptor = call.getMethodDescriptor();
        String methodName = methodDescriptor.getFullMethodName();

        log.info("[gRPC Request] Method: {}", methodName);

        // レスポンス送信時のフック
        ServerCall<ReqT, RespT> loggingCall = new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {
            @Override
            public void close(Status status, Metadata trailers) {
                long duration = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);

                if (status.isOk()) {
                    log.info("[gRPC Success] Method: {} | Duration: {}ms", methodName, duration);
                } else {
                    log.error("[gRPC Error] Method: {} | Duration: {}ms | Status: {} | Description: {}",
                            methodName, duration, status.getCode(), status.getDescription());
                }
                super.close(status, trailers);
            }
        };

        return next.startCall(loggingCall, headers);
    }
}