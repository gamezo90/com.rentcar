package com.rentcar.service.impl;

import com.rentcar.domain.Order;
import com.rentcar.repository.OrderRepository;
import com.rentcar.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public Order findOrdersByCarId(Long id) {
        return null;
    }

    @Override
    public Order findOrdersByUserId(Long id) {
        return null;
    }

    @Override
    public Order create(Order order) {
        return null;
    }

    @Override
    public Order update(Order order) {
        return null;
    }
}
