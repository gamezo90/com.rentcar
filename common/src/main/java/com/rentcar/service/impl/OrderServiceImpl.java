package com.rentcar.service.impl;

import com.rentcar.domain.Order;
import com.rentcar.domain.User;
import com.rentcar.repository.CarRepository;
import com.rentcar.repository.OrderRepository;
import com.rentcar.service.CarService;
import com.rentcar.service.OrderService;
import com.rentcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.rentcar.service.impl.DiscountServiceImpl.localDate;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserService userService;

    private final CarService carService;


    @Override
    public List<Order> findAll() {
        if (orderRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException(String.format("Orders not found"));
        }
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Order with this id %s not found", id)));
    }

    @Override
    public List<Order> findOrdersByCarId(Long id) {
//        if (orderRepository.findAll().isEmpty()) {
//            throw new EntityNotFoundException(String.format("Orders not found"));
//        }
        return orderRepository.findOrdersByCarId(id);
    }

    @Override
    public List<Order> findOrdersByUserId(Long id) {
        if (orderRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException(String.format("Orders not found"));
        }
        return orderRepository.findOrdersByUserId(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 100, rollbackFor = Exception.class)
    @Override
    public Order create(Order order) {
        order.setCreationDate(new Timestamp(new Date().getTime()));
        order.setModificationDate(new Timestamp(new Date().getTime()));
        if(order.getExpirationDate().isBefore(localDate)) {
            throw new IllegalArgumentException(
                    String.format("Expiration date must be future"));
        }
        orderRepository.save(order);
        return orderRepository.findById(order.getId()).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 100, rollbackFor = Exception.class)
    @Override
    public Order update(Order order) {
        order.setModificationDate(new Timestamp(new Date().getTime()));
        if(order.getExpirationDate().isBefore(localDate)) {
            throw new IllegalArgumentException(
                    String.format("Expiration date must be future"));
        }
        orderRepository.save(order);
        return orderRepository.findById(order.getId()).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Order> findByUserLogin(String login) {
        if (orderRepository.findByUserCredentialsLogin(login) == null) {
            throw new EntityNotFoundException(String.format("Orders for User with login %s not found", login));
        }
        return orderRepository.findByUserCredentialsLogin(login);
    }
}
