package com.rentcar.service.impl;

import com.rentcar.domain.Order;
import com.rentcar.exception.NoSuchEntityException;
import com.rentcar.repository.OrderRepository;
import com.rentcar.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("Order with this id \"%s\" not found", id)));
    }

    @Override
    public List<Order> findOrdersByCarId(Long id) {
        return orderRepository.findOrdersByCarId(id);
    }

    @Override
    public List<Order> findOrdersByUserId(Long id) {
        return orderRepository.findOrdersByUserId(id);
    }

    @Override
    public Order create(Order order) {
        order.setCreationDate(new Timestamp(new Date().getTime()));
        order.setModificationDate(new Timestamp(new Date().getTime()));
        orderRepository.save(order);
        return orderRepository.findById(order.getId()).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Order update(Order order) {
        order.setModificationDate(new Timestamp(new Date().getTime()));
        orderRepository.save(order);
        return orderRepository.findById(order.getId()).orElseThrow(IllegalArgumentException::new);
    }
}
