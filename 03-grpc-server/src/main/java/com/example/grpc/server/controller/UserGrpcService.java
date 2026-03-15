package com.example.grpc.server.controller;

import com.example.grpc.core.entity.UserEntity;
import com.example.proto.UserServiceGrpc;
import com.example.proto.UserServiceProto;
import com.example.grpc.core.service.UserDomainService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private final UserDomainService userDomainService;

    @Override
    public void getUser(UserServiceProto.UserRequest request, StreamObserver<UserServiceProto.User> responseObserver) {
        // DBから取得
        UserEntity entity = userDomainService.findById(request.getId());

        // Entity -> gRPC Message 変換
        UserServiceProto.User response = UserServiceProto.User.newBuilder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setEmail(entity.getEmail())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createUser(UserServiceProto.CreateUserRequest request, StreamObserver<UserServiceProto.User> responseObserver) {
        // ビジネスロジック実行
        UserEntity entity = userDomainService.insert(new UserEntity(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getEmail(),
                "",
                "")

        );

        // レスポンス作成
        UserServiceProto.User response = UserServiceProto.User.newBuilder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setEmail(entity.getEmail())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}