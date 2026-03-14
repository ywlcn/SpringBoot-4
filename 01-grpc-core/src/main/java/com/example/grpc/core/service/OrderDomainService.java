package com.example.grpc.core.service;

import com.example.grpc.core.entity.OrderEntity;

public interface OrderDomainService {
    OrderEntity findById(String id);
    OrderEntity insertOrder(OrderEntity order);


}
