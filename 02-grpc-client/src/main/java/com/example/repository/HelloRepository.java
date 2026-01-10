package com.example.repository;

import com.example.mapper.HelloMapper;
import com.example.repository.dto.HelloRepositoryInDto;
import com.example.repository.dto.HelloRepositoryOutDto;

public interface HelloRepository {

    HelloMapper mapper = HelloMapper.INSTANCE;

    HelloRepositoryOutDto sayHello(HelloRepositoryInDto inDto);

}
