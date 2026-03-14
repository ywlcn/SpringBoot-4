package com.example.grpc.client.mapper;

import com.example.grpc.client.controller.dto.HelloRequestDto;
import com.example.grpc.client.controller.dto.HelloResponseDto;
import com.example.proto.SampleProto;
import com.example.grpc.client.repository.dto.HelloRepositoryInDto;
import com.example.grpc.client.repository.dto.HelloRepositoryOutDto;
import com.example.grpc.client.service.dto.HelloServiceInDto;
import com.example.grpc.client.service.dto.HelloServiceOutDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface HelloMapper {

    HelloMapper INSTANCE = Mappers.getMapper(HelloMapper.class);

    // Rest dto -> service dto -> repository dto -> SampleProto.HelloRequest
    HelloServiceInDto mapToDto(HelloRequestDto dto);

    HelloRepositoryInDto mapToDto(HelloServiceInDto dto);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", source = "name")
    SampleProto.HelloRequest mapToProto(HelloRepositoryInDto dto);


    // SampleProto.HelloReply -> repository dto -> service dto -> Rest dto
    @Mapping(target = "message", source = "message")
    HelloRepositoryOutDto mapToDto(SampleProto.HelloReply proto);

    HelloServiceOutDto mapToDto(HelloRepositoryOutDto dto);

    HelloResponseDto mapToDto(HelloServiceOutDto dto);

//    @Mapping(source = "permissions", target = "permissionsList")
//    @Mapping(source = "mainDepartments", target = "mainDepartmentsList")
//    @Mapping(source = "departments", target = "departmentsList")
//    UserDTO map(User user);
//
//    @Mapping(source = "permissionsList", target = "permissions")
//    @Mapping(source = "mainDepartmentsList", target = "mainDepartments")
//    @Mapping(source = "departmentsList", target = "departments")
//    User map(UserDTO userDTO);
//
//    @ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.NULL)
//    Permission map(PermissionDTO permissionDTO);
//
//    PermissionDTO map(Permission perm);
//
//
//    Department map(DepartmentDTO departmentDTO);
//    DepartmentDTO map(Department department);
}