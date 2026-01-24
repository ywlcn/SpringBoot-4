package com.example.interceptor;

import io.grpc.ClientInterceptor;
import org.springframework.grpc.client.ClientInterceptorFilter;

public class MyClientInterceptorFilter implements ClientInterceptorFilter {



    @Override
    public boolean filter(ClientInterceptor interceptor) {


        return false;
    }
}
