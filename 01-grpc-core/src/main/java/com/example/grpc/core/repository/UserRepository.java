package com.example.grpc.core.repository;

import com.example.grpc.core.entity.UserEntity;

import java.util.List;

public interface UserRepository {
    UserEntity findById(String id);
    UserEntity findByName(String name);
    List<UserEntity> findAll();
    void insert(UserEntity user);
    void update(UserEntity user);
    void delete(String id);
}
