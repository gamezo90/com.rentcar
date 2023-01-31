package com.rentcar.controller;

import com.rentcar.controller.mappers.OrderMapper;
import com.rentcar.controller.requests.OrdersRequests.OrderCreateRequest;
import com.rentcar.controller.requests.OrdersRequests.OrderUpdateRequest;
import com.rentcar.controller.response.OrderResponse;
import com.rentcar.domain.Order;
import com.rentcar.service.CarService;
import com.rentcar.service.OrderService;
import com.rentcar.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@Tag(name = "Order controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    private final UserService userService;

    private final CarService carService;


    @Operation(summary = "Find all orders", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    @PreAuthorize(value = "hasAnyRole('ADMIN','MODERATOR')")
    @GetMapping("/findAllOrders")
    public ResponseEntity<Object> findAllOrders() {

        return new ResponseEntity<>(Collections.singletonMap("result",
                orderService.findAll()), HttpStatus.OK);
    }

    @Operation(summary = "Find order by id", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    @PreAuthorize(value = "hasAnyRole('ADMIN','MODERATOR')")
    @GetMapping("/findOrderById")
    public ResponseEntity<Object> findOrderById(@RequestParam("id") Long orderId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                orderService.findById(orderId)), HttpStatus.OK);
    }

    @Operation(summary = "Find orders by car id", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    @PreAuthorize(value = "hasAnyRole('ADMIN','MODERATOR')")
    @GetMapping("/findOrderByCarId")
    public ResponseEntity<Object> findOrdersByCarId(@RequestParam("id") Long carId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                orderService.findOrdersByCarId(carId)), HttpStatus.OK);
    }

    @Operation(summary = "Find orders by user id", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    @PreAuthorize(value = "hasAnyRole('ADMIN','MODERATOR')")
    @GetMapping("/findOrdersByUserId")
    public ResponseEntity<Object> findOrdersByUserId(@RequestParam("id") Long userId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                orderService.findOrdersByUserId(userId)), HttpStatus.OK);
    }

    @Operation(summary =  "Adding order", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    @PreAuthorize(value = "#principal.getName() == authentication.name")
    @PostMapping("/createOrder")
    public ResponseEntity<Object> addOrder(@Valid @RequestBody OrderCreateRequest createRequest) {
        Order newOrder = orderMapper.orderConvertCreateRequest(createRequest);
        userService.findById(newOrder.getUserId());
        carService.findByCarId(newOrder.getCarId());
        OrderResponse response = orderMapper.toResponse(orderService.create(newOrder));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Update order", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    @PutMapping("/updateOrder")
    public ResponseEntity<Object> updateCar(@RequestParam("id") Long id, @Valid @RequestBody OrderUpdateRequest orderUpdateRequest) {
        Order updatedOrder = orderMapper.convertUpdateRequest(orderUpdateRequest, orderService.findById(id));
        OrderResponse orderResponse = orderMapper.toResponse(orderService.update(updatedOrder));
        return new ResponseEntity<>(Collections.singletonMap("cars", orderResponse), HttpStatus.OK);
    }

    @Operation(summary = "Find discount by user login", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    @PreAuthorize("#login == authentication.name")
    @GetMapping("/findDiscountByUserLogin")
    public ResponseEntity<Object> findByUserLogin(@RequestParam("userLogin") String login) {

        return new ResponseEntity<>(Collections.singletonMap("result", orderService.findByUserLogin(login)), HttpStatus.OK);
    }

}

