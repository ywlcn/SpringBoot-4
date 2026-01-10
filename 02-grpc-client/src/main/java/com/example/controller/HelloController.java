package com.example.controller;

import com.example.controller.dto.HelloRequestDto;
import com.example.controller.dto.HelloResponseDto;
import com.example.mapper.HelloMapper;
import com.example.service.HelloService;
import com.example.service.dto.HelloServiceInDto;
import com.example.service.dto.HelloServiceOutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/")
@RestController
public class HelloController {

    HelloMapper mapper = HelloMapper.INSTANCE;

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public ResponseEntity<HelloResponseDto> getHelloAdvanced(HelloRequestDto requestDto) {

        HelloServiceInDto serviceInDto = mapper.mapToDto(requestDto);
        HelloServiceOutDto response = helloService.sayHello(serviceInDto);
        HelloResponseDto responseDto = mapper.mapToDto(response);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Custom-Header", "gRPC-Integrated-Value");
        headers.add("X-Server-ID", "server-01");

        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

}
