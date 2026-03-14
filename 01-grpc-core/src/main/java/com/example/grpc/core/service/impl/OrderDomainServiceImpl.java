package com.example.grpc.core.service.impl;


import com.example.grpc.core.entity.OrderEntity;
import com.example.grpc.core.repository.OrderRepository;
import com.example.grpc.core.service.OrderDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderDomainServiceImpl implements OrderDomainService {

    private final OrderRepository orderRepository;

    @Override
    public OrderEntity findById(String orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public OrderEntity insertOrder(OrderEntity order) {
        String orderId = UUID.randomUUID().toString();

        // 1. Order本体の保存
        orderRepository.insertOrder(order);

        // 2. 明細の保存
        orderRepository.insertOrderItems(order.getItems());

        return orderRepository.findById(orderId);
    }


}