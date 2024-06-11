package org.example.service;
import org.example.entity.Order;
import org.example.entity.OrderItem;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    public Order saveOrder(Order order) {
        calculateTotalAmount(order);
        return orderRepository.save(order);
    }
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
    public List<Order> getOrdersByCustomerEmail(String customerEmail) {
        return orderRepository.findByCustomerEmail(customerEmail);
    }
    private void calculateTotalAmount(Order order) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderItem orderItem : order.getOrderItems()) {
            totalAmount = totalAmount.add(orderItem.getTotalPrice());
        }
        order.setTotalAmount(totalAmount);
    }
}