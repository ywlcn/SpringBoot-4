package com.example.grpc.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {
    private Integer id;
    private String orderId;
    private String itemName;
    private Integer price;
}