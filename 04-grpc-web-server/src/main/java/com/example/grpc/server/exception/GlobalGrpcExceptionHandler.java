package com.example.grpc.server.exception;

import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusException;
import org.springframework.grpc.server.exception.GrpcExceptionHandler;
import org.springframework.stereotype.Component;

@Component
public class GlobalGrpcExceptionHandler implements GrpcExceptionHandler {

    @Override
    public StatusException handleException(Throwable ex) {

        Metadata metadata = new Metadata();
        Metadata.Key<String> errorKey = Metadata.Key.of("error-code", Metadata.ASCII_STRING_MARSHALLER);

        // 2. 处理参数校验异常（如 Spring 的验证异常）
        if (ex instanceof IllegalArgumentException) {
            metadata.put(errorKey, "INVALID_INPUT");
            StatusException statusException =
                    new StatusException(Status.INVALID_ARGUMENT.withDescription("参数错误: " + ex.getMessage()), metadata);
            return statusException;
        }
        metadata.put(errorKey, "INTERNAL111");

        return  new StatusException(Status.INTERNAL.withDescription("服务器内部错误"), metadata);

    }
}