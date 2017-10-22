package com.charles.adminapi.web;

import com.charles.adminapi.domain.Order;
import com.charles.adminapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "orders")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping(value = "orders/{orderId}")
    public Order findOne(@PathVariable final Integer orderId) {
        return orderService.findOne(orderId);
    }

    @PostMapping(value = "orders")
    public Order save(@RequestBody final Order order) {
        return orderService.saveOne(order);
    }
}
