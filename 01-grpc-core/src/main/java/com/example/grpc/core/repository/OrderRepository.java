package com.example.grpc.core.repository;

import com.example.grpc.core.entity.OrderEntity;
import com.example.grpc.core.entity.OrderItemEntity;

import java.util.List;

public interface OrderRepository {
    OrderEntity findById(String id);
    void insertOrder(OrderEntity order);
    void insertOrderItems(List<OrderItemEntity> items);
}
