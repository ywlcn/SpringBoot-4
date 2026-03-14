package com.example.grpc.core.repository.impl;

import com.example.grpc.core.entity.UserEntity;
import com.example.grpc.core.repository.UserRepository;
import com.example.grpc.core.repository.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {


    private final UserMapper userMapper;


    @Override
    public UserEntity findById(String id) {
        return userMapper.findById(id);
    }

    @Override
    public void insert(UserEntity user) {
        userMapper.insert(user);
    }

    @Override
    public void update(UserEntity user) {
        userMapper.update(user);
    }

    @Override
    public void delete(String id) {
        userMapper.delete(id);
    }
}
