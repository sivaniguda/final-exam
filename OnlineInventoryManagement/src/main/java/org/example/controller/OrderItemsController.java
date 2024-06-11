package org.example.controller;

import org.example.entity.Order;
import org.example.entity.OrderItem;
import org.example.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderItems")
public class OrderItemsController {

    @Autowired
    private OrderItemsService orderItemsService;

    @PostMapping("/createOrderItems")
    public OrderItem createOrderItems(@RequestBody OrderItem orderItem){
        return orderItemsService.createOrderItems(orderItem);
    }
}
