package com.charles.adminapi.service;


import com.charles.adminapi.domain.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    Order findOne(Integer orderId);

    Order saveOne(Order order);
}
