package com.example.service;

import com.example.mapper.HelloMapper;
import com.example.service.dto.HelloServiceInDto;
import com.example.service.dto.HelloServiceOutDto;

public interface HelloService {

    HelloMapper mapper = HelloMapper.INSTANCE;

    HelloServiceOutDto sayHello(HelloServiceInDto inDto);


}
