package com.example.grpc.client.interceptor;

import com.example.grpc.core.entity.UserEntity;
import com.example.grpc.core.repository.UserRepository;
import com.example.grpc.security.internal.SampleUser;
import io.grpc.*;
import lombok.AllArgsConstructor;
import org.springframework.grpc.client.interceptor.security.BasicAuthenticationInterceptor;
import org.springframework.grpc.server.security.GrpcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;
import java.util.Objects;

@AllArgsConstructor
public class MyBasicAuthenticationInterceptor implements ClientInterceptor {


    private final UserRepository userRepository;


    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method,
                                                               CallOptions callOptions, Channel next) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assert auth != null;
        String userName =auth.getName();
        UserEntity userEntity = userRepository.findByName(userName);

        String authValue = "Basic " + Base64.getEncoder()
                .encodeToString((userName + ":" + userEntity.getPassword()).getBytes());

        return new ForwardingClientCall.SimpleForwardingClientCall<>(next.newCall(method, callOptions)) {
            public void start(ClientCall.Listener<RespT> responseListener, io.grpc.Metadata headers) {
                headers.put(GrpcSecurity.AUTHORIZATION_KEY, authValue);
                super.start(responseListener, headers);
            }
        };
    }

}