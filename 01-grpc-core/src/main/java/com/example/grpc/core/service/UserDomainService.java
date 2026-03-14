package com.example.grpc.core.service;

import com.example.grpc.core.entity.UserEntity;

public interface UserDomainService {
    UserEntity findById(String id);
    UserEntity insert(UserEntity user);
    UserEntity update(UserEntity user);
    void delete(String id);
}
