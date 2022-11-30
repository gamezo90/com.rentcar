package com.rentcar.controller;

import com.rentcar.controller.mappers.OrderMapper;
import com.rentcar.controller.requests.CarsRequests.CarCreateRequest;
import com.rentcar.controller.requests.OrdersRequests.OrderCreateRequest;
import com.rentcar.controller.response.CarsResponse;
import com.rentcar.controller.response.OrderResponce;
import com.rentcar.domain.Car;
import com.rentcar.domain.Order;
import com.rentcar.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService repository;

    private final OrderMapper orderMapper;


    @GetMapping("/findAllOrders")
    public ResponseEntity<Object> findAllOrders() {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findAll()), HttpStatus.OK);
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

    @PostMapping("/createOrder")
    @Transactional
    public ResponseEntity<Object> addOrder(@Valid @RequestBody OrderCreateRequest createRequest) {
        Order newOrder = orderMapper.orderConvertCreateRequest(createRequest);
        OrderResponce response = orderMapper.toResponse(repository.create(newOrder));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/updateOrder")
    @Transactional
    public ResponseEntity<Object> updateOrder(@Valid @RequestBody OrderUpdateRequest updateRequest) {
        Order newOrder = orderMapper.OrderUpdateRequest(updateRequest);
        OrderResponce response = orderMapper.toResponse(repository.update(newOrder));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
