package com.example.mapper;

import com.example.controller.dto.HelloRequestDto;
import com.example.controller.dto.HelloResponseDto;
import com.example.proto.HelloWorldProto;
import com.example.repository.dto.HelloRepositoryInDto;
import com.example.repository.dto.HelloRepositoryOutDto;
import com.example.service.dto.HelloServiceInDto;
import com.example.service.dto.HelloServiceOutDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface HelloMapper {

    HelloMapper INSTANCE = Mappers.getMapper(HelloMapper.class);

    // Rest dto -> service dto -> repository dto -> HelloWorldProto.HelloRequest
    HelloServiceInDto mapToDto(HelloRequestDto dto);

    HelloRepositoryInDto mapToDto(HelloServiceInDto dto);

    @BeanMapping(ignoreByDefault = true) // 忽略所有默认映射
//    @Mapping(target = "name", source = "name") // 只映射你需要的字段
    HelloWorldProto.HelloRequest mapToProto(HelloRepositoryInDto dto);
    //default HelloWorldProto.HelloRequest mapToProto(HelloRepositoryInDto dto) {
    //    return HelloWorldProto.HelloRequest.newBuilder().setName(dto.getName()).build();
    //}

    // HelloWorldProto.HelloReply -> repository dto -> service dto -> Rest dto
    HelloRepositoryOutDto mapToDto(HelloWorldProto.HelloReply proto);

    // default HelloRepositoryOutDto mapToDto(HelloWorldProto.HelloReply proto) {
    //     HelloRepositoryOutDto outDto = new HelloRepositoryOutDto();
    //     outDto.setMessage(proto.getMessage());
    //     return outDto;
    // }
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