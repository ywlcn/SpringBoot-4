package com.example.grpc.core.repository.impl;

import com.example.grpc.core.entity.UserEntity;
import com.example.grpc.core.repository.UserRepository;
import com.example.grpc.core.repository.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {


    private final UserMapper userMapper;


    @Override
    public UserEntity findById(String id) {
        return userMapper.findById(id);
    }

    @Override
    public UserEntity findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public List<UserEntity> findAll() {
        return userMapper.findAll();
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
