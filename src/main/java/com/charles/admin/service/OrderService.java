package com.charles.admin.service;


import com.charles.admin.domain.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    Order findOne(Integer orderId);

    Order saveOne(Order order);
}
