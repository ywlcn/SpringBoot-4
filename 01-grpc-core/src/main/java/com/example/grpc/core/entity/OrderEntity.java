package com.example.grpc.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    private String id;
    private String userId;
    private double totalAmount;
    private String status;
    private List<OrderItemEntity> items;
}

