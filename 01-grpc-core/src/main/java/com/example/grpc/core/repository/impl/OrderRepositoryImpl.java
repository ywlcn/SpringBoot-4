package com.example.grpc.core.repository.impl;

import com.example.grpc.core.entity.OrderEntity;
import com.example.grpc.core.entity.OrderItemEntity;
import com.example.grpc.core.repository.OrderRepository;
import com.example.grpc.core.repository.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderMapper orderMapper;

    @Override
    public OrderEntity findById(String id) {
        return orderMapper.findById(id);
    }

    @Override
    public void insertOrder(OrderEntity order) {
        orderMapper.insertOrder(order);
    }

    @Override
    public void insertOrderItems(List<OrderItemEntity> items) {
        orderMapper.insertOrderItems(items);
    }
}
