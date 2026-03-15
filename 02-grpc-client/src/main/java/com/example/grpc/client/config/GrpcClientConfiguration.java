package com.example.grpc.client.config;

import com.example.grpc.client.interceptor.ExtraThingsInterceptor;
import com.example.grpc.client.interceptor.LoggingInterceptor;
import com.example.proto.SampleGrpc;
import io.grpc.Channel;
import io.grpc.ClientInterceptor;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.NettyChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.grpc.client.*;
import org.springframework.grpc.client.interceptor.security.BasicAuthenticationInterceptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;

@Configuration
@ImportGrpcClients(target = "local", types = {SampleGrpc.SampleBlockingStub.class},
        basePackages = "com.example.grpc.client.repository")
public class GrpcClientConfiguration {

//    @Bean
//    SimpleGrpc.SimpleBlockingStub stub(GrpcChannelFactory channels) {
//        return SimpleGrpc.newBlockingStub(channels.createChannel("0.0.0.0:9090"));
//    }

//    @Bean
//    SimpleGrpc.SimpleBlockingStub stub(GrpcChannelFactory channels) {
//        return SimpleGrpc.newBlockingStub(channels.createChannel("local"));
//    }

    @Bean
    @Order(100)
    GrpcChannelBuilderCustomizer<NettyChannelBuilder> flowControlCustomizer() {
        return (name, builder) -> builder.flowControlWindow(1024 * 1024);
    }

    @Bean
    @Order(200)
    <T extends ManagedChannelBuilder<T>> GrpcChannelBuilderCustomizer<T> retryChannelCustomizer() {
        return (name, builder) -> builder.enableRetry().maxRetryAttempts(5);
    }

    ///    リクエスト：　　LoggingInterceptor　　ー＞　　ExtraThingsInterceptor
    /// 　　レスポンス：　　ExtraThingsInterceptor　　ー＞　　LoggingInterceptor
    @Bean
    @Order(100)
    @GlobalClientInterceptor
    ClientInterceptor globalLoggingInterceptor() {
        return new LoggingInterceptor();
    }

    @Bean
    @Order(200)
    @GlobalClientInterceptor
    ClientInterceptor globalExtraThingsInterceptor() {
        return new ExtraThingsInterceptor();
    }

//    @Bean
//    ClientInterceptorFilter myInterceptorFilter() {
//        return (interceptor, factory) -> !(interceptor instanceof ExtraThingsInterceptor
//                && factory instanceof InProcessGrpcChannelFactory);
//    }


    @Bean
    @Lazy
    Channel basic(GrpcChannelFactory channels) {
        return channels.createChannel("my-channel", ChannelBuilderOptions.defaults()
                .withInterceptors(List.of(new BasicAuthenticationInterceptor("user", "password"))));
    }


//    @Bean
//    @Lazy
//    SimpleGrpc.SimpleBlockingStub basic(GrpcChannelFactory channels, ClientRegistrationRepository registry) {
//        ClientRegistration reg = registry.findByRegistrationId("spring");
//        return SimpleGrpc.newBlockingStub(channels.createChannel("0.0.0.0:9090", ChannelBuilderOptions.defaults()
//                .withInterceptors(List.of(new BearerTokenAuthenticationInterceptor(() -> token(reg))))));
//    }
//
//    private String token(ClientRegistration reg) {
//        RestClientClientCredentialsTokenResponseClient creds = new RestClientClientCredentialsTokenResponseClient();
//        String token = creds.getTokenResponse(new OAuth2ClientCredentialsGrantRequest(reg))
//                .getAccessToken()
//                .getTokenValue();
//        return token;
//    }


}
