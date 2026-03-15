package com.example.grpc.core.repository.mapper;

import com.example.grpc.core.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserEntity findById(String id);
    UserEntity findByName(String name);
    List<UserEntity> findAll();
    void insert(UserEntity user);
    void update(UserEntity user);
    void delete(String id);
}
