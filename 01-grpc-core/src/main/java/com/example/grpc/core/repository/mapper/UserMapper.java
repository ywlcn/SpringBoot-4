package com.example.grpc.core.repository.mapper;

import com.example.grpc.core.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserEntity findById(String id);
    void insert(UserEntity user);
    void update(UserEntity user);
    void delete(String id);
}
