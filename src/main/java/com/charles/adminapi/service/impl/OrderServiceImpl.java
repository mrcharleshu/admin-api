package com.charles.adminapi.service.impl;


import com.charles.adminapi.domain.Order;
import com.charles.adminapi.repository.OrderRepository;
import com.charles.adminapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOne(Integer orderId) {
        return orderRepository.findOne(orderId);
    }

    @Override
    public Order saveOne(Order order) {
        return orderRepository.save(order);
    }
}
