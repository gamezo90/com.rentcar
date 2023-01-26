package com.rentcar.repository;

import com.rentcar.domain.Car;
import com.rentcar.domain.Discount;
import com.rentcar.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Order, Long> {
    List<Order> findOrdersByUserId(Long userId);

    List<Order> findOrdersByCarId(Long carId);

    List<Order> findByUserCredentialsLogin(String login);
}
