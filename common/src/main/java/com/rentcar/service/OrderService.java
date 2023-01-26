package com.rentcar.service;

import com.rentcar.domain.Order;
import com.rentcar.domain.User;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order findById(Long id);

    List<Order> findOrdersByCarId(Long id);

    List<Order> findOrdersByUserId(Long id);

    Order create(Order order);

    Order update(Order order);

    List<Order> findByUserLogin(String login);

}
