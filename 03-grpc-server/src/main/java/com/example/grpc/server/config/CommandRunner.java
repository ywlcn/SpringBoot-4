package com.example.grpc.server.config;

import io.grpc.BindableService;
import io.grpc.ServerServiceDefinition;
import io.grpc.health.v1.HealthCheckResponse;
import io.grpc.protobuf.services.HealthStatusManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.grpc.server.DefaultGrpcServerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

@Component
public class CommandRunner implements CommandLineRunner {

    @Autowired
    HealthStatusManager healthStatusManager;

    @Autowired
    ApplicationContext ap;

    @Override
    public void run(String... args) throws Exception {

        DefaultGrpcServerFactory factory = ap.getBean(DefaultGrpcServerFactory.class);
        Field serviceListField = DefaultGrpcServerFactory.class.getDeclaredField("serviceList");
        serviceListField.setAccessible(true);
        List<ServerServiceDefinition> serviceList = (List<ServerServiceDefinition>) serviceListField.get(factory);

//        serviceList.forEach( f -> {
//            healthStatusManager.setStatus(f.getServiceDescriptor().getName(), HealthCheckResponse.ServingStatus.SERVING);
//        });

    }
}
