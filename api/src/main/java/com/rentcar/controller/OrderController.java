package com.rentcar.controller;

import com.rentcar.controller.mappers.OrderMapper;
import com.rentcar.controller.requests.OrdersRequests.OrderCreateRequest;
import com.rentcar.controller.requests.OrdersRequests.OrderUpdateRequest;
import com.rentcar.controller.response.OrderResponse;
import com.rentcar.domain.Order;
import com.rentcar.service.CarService;
import com.rentcar.service.OrderService;
import com.rentcar.service.UserService;
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

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    private final UserService userService;

    private final CarService carService;


    @GetMapping("/findAllOrders")
    public ResponseEntity<Object> findAllOrders() {

        return new ResponseEntity<>(Collections.singletonMap("result",
                orderService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/findOrderById")
    public ResponseEntity<Object> findOrderById(@RequestParam("id") Long orderId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                orderService.findById(orderId)), HttpStatus.OK);
    }

    @GetMapping("/findOrderByCarId")
    public ResponseEntity<Object> findOrdersByCarId(@RequestParam("id") Long carId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                orderService.findOrdersByCarId(carId)), HttpStatus.OK);
    }

    @GetMapping("/findOrdersByUserId")
    public ResponseEntity<Object> findOrdersByUserId(@RequestParam("id") Long userId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                orderService.findOrdersByUserId(userId)), HttpStatus.OK);
    }

    @PostMapping("/createOrder")
    @Transactional
    public ResponseEntity<Object> addOrder(@Valid @RequestBody OrderCreateRequest createRequest) {
        Order newOrder = orderMapper.orderConvertCreateRequest(createRequest);
        userService.findById(newOrder.getUserId());
        carService.findById(newOrder.getCarId());
        OrderResponse response = orderMapper.toResponse(orderService.create(newOrder));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/updateOrder")
    @Transactional
    public ResponseEntity<Object> updateCar(@RequestParam("id") Long id, @Valid @RequestBody OrderUpdateRequest orderUpdateRequest) {
        Order updatedOrder = orderMapper.convertUpdateRequest(orderUpdateRequest, orderService.findById(id));
        OrderResponse orderResponse = orderMapper.toResponse(orderService.update(updatedOrder));
        return new ResponseEntity<>(Collections.singletonMap("cars", orderResponse), HttpStatus.OK);
    }
}
