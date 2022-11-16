package com.rentcar.controller;

import com.rentcar.repository.OrderRepository;
import com.rentcar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository repository;

    @GetMapping("/findAllOrders")
    public ResponseEntity<Object> findAllOrders() {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findAll(PageRequest.of(0, 10))), HttpStatus.OK);
    }

    @GetMapping("/findOrderById")
    public ResponseEntity<Object> findOrderById(@RequestParam("id") Long orderId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findById(orderId)), HttpStatus.OK);
    }

    @GetMapping("/findOrderByCarId")
    public ResponseEntity<Object> findOrdersByCarId(@RequestParam("id") Long carId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findOrdersByCarId(carId)), HttpStatus.OK);
    }

    @GetMapping("/findOrdersByUserId")
    public ResponseEntity<Object> findOrdersByUserId(@RequestParam("id") Long userId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findOrdersByUserId(userId)), HttpStatus.OK);
    }
}
