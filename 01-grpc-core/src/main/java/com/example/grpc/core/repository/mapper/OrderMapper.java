package com.example.grpc.core.repository.mapper;


import com.example.grpc.core.entity.OrderEntity;
import com.example.grpc.core.entity.OrderItemEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderEntity findById(String id);
    void insertOrder(OrderEntity order);
    void insertOrderItems(List<OrderItemEntity> items);
}
