package com.example.grpc.client.interceptor;

import io.grpc.*;
import org.springframework.grpc.client.interceptor.security.BasicAuthenticationInterceptor;
import org.springframework.grpc.server.security.GrpcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Base64;
import java.util.Objects;

public class MyBasicAuthenticationInterceptor  implements ClientInterceptor {

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method,
                                                               CallOptions callOptions, Channel next) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assert auth != null;
        String username = auth.getName();
        String password = Objects.requireNonNull(auth.getCredentials()).toString();

        String authValue = "Basic " + Base64.getEncoder()
                .encodeToString((username + ":" + password).getBytes());

        return new ForwardingClientCall.SimpleForwardingClientCall<>(next.newCall(method, callOptions)) {
            public void start(ClientCall.Listener<RespT> responseListener, io.grpc.Metadata headers) {
                headers.put(GrpcSecurity.AUTHORIZATION_KEY,authValue);
                super.start(responseListener, headers);
            }
        };
    }

}