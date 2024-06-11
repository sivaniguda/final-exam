package org.example.service;

import org.example.entity.OrderItem;
import org.example.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsService {

    @Autowired
    OrderItemRepository orderItemRepository;

    public OrderItem createOrderItems(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }


}
