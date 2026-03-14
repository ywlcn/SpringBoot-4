package com.example.grpc.core.service.impl;

import com.example.grpc.core.entity.UserEntity;
import com.example.grpc.core.repository.UserRepository;
import com.example.grpc.core.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDomainServiceImpl implements UserDomainService {

    private final UserRepository userRepository;

    @Override
    public UserEntity findById(String id) {
        return userRepository.findById(id);
    }

    @Transactional
    @Override
    public UserEntity insert(UserEntity entity) {
        userRepository.insert(entity);
        return entity;
    }

    @Transactional
    @Override
    public UserEntity update(UserEntity user) {
        userRepository.update(user);
        return user;
    }

    @Transactional
    @Override
    public void delete(String id) {
        userRepository.delete(id);
    }
}
