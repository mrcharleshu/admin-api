package com.charles.admin.web;

import com.charles.admin.domain.Order;
import com.charles.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
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
