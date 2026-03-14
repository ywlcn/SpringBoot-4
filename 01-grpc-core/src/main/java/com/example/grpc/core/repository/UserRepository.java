package com.example.grpc.core.repository;

import com.example.grpc.core.entity.UserEntity;

public interface UserRepository {
    UserEntity findById(String id);
    void insert(UserEntity user);
    void update(UserEntity user);
    void delete(String id);
}
