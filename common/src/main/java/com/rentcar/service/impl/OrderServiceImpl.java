package com.rentcar.service.impl;

import com.rentcar.domain.Order;
import com.rentcar.repository.OrderRepository;
import com.rentcar.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void create(Order order) {

        orderRepository.save(order);
    }
}
