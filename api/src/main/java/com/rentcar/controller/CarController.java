package com.rentcar.controller;


import com.rentcar.controller.mappers.CarMapper;
import com.rentcar.controller.requests.CarsRequests.CarCreateRequest;
import com.rentcar.controller.requests.CarsRequests.CarUpdateRequest;
import com.rentcar.controller.response.CarsResponse;
import com.rentcar.domain.Car;
import com.rentcar.domain.Order;
import com.rentcar.exception.ForbiddenException;
import com.rentcar.service.CarService;
import com.rentcar.service.DiscountService;
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
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.rentcar.service.impl.DiscountServiceImpl.localDate;

@Tag(name = "Car controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    private final CarMapper carMapper;

    private final UserService userService;

    private final OrderService orderService;

    private final DiscountService discountService;

    @Operation(summary = "Find car by car id", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(type = "string"))
    })
    @PreAuthorize(value = "hasAnyRole('MODERATOR', 'ADMIN')")
    @GetMapping("/findCarByCarId")
    public ResponseEntity<Object> findCarByCarId(@RequestParam("id") Long carId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                carService.findByCarId(carId)), HttpStatus.OK);
    }

    @Operation(summary = "Find car by car id", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(type = "string"))
    })
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/findAllCars")
    public ResponseEntity<Object> findAllCars() {

        return new ResponseEntity<>(
                Collections.singletonMap("result", carService.findAll()),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Find all available cars")
    @GetMapping("/findAllAvailableCars")
    public ResponseEntity<Object> findAllAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        Stream.of(orderService.findAll().stream().filter(order
                -> order.getExpirationDate().isBefore(localDate)).map(order
                -> order.getCar()).filter(car
                -> car.getIsBanned() == false).collect(Collectors.toList()),
                carService.findAll().stream().filter(car
                ->  car.getOrders().isEmpty() & car.getIsBanned() == false).collect(Collectors.toList())).forEach(availableCars::addAll);

        List<CarsResponse> response = carMapper.toResponse(availableCars);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Find all available cars for authentication users", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(type = "string"))
    })
    @PreAuthorize(value = "#principal.getName() == authentication.name")
    @GetMapping("/findAllAvailableCarsForAuthenticationUsers")
    public ResponseEntity<Object> findAllAvailableCarsForAuthenticationUsers(Principal principal) {
        List<Car> availableCars = new ArrayList<>();

        long i = 0;
        orderService.findOrdersByCarId(i).stream().anyMatch(order -> order.getExpirationDate().isAfter(localDate));

        List<Order> test = new ArrayList<>();
        test.addAll(orderService.findAll());
        //то удаляем из листа
        test.remove(orderService.findOrdersByCarId(i).stream().anyMatch(order -> order.getExpirationDate().isAfter(localDate)));


        Stream.of(orderService.findAll().stream().filter(order
                        -> order.getExpirationDate().isBefore(localDate))


                        .map(order
                        -> order.getCar()).filter(car
                        -> car.getIsBanned() == false).collect(Collectors.toList()),

                carService.findAll().stream().filter(car
                        ->  car.getOrders().isEmpty() & car.getIsBanned() == false)
                        .collect(Collectors.toList())).forEach(availableCars::addAll);

                availableCars.stream().forEach(car
                        -> car.setPrice(Double.valueOf(Math.round(car.getPrice()
                        * (1 - discountService.findUserDiscountSize(principal.getName()) / 100)))));
        List<CarsResponse> response = carMapper.toResponse(availableCars);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Find cars by user login", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(type = "string"))
    })
    @GetMapping("/findCarsByUserLogin")
    public ResponseEntity<Object> findCarsByUserLogin(@RequestParam("user_login") String login) {
        carService.checkCarWithUserLoginExist(login);
        return new ResponseEntity<>(Collections.singletonMap("result", carService.findByUserLogin(login)), HttpStatus.OK);
    }

    @Operation(summary = "Adding a car", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(type = "string"))
    })
    @PreAuthorize(value = "#principal.getName() == authentication.name")
    @PostMapping("/createCar")
    public ResponseEntity<Object> addCar(@Valid @RequestBody CarCreateRequest createRequest, Principal principal) {
        Car newCar = carMapper.carConvertCreateRequest(createRequest);
        newCar.setUserId(userService.findByLogin(principal.getName()).getId());
        CarsResponse response = carMapper.toResponse(carService.create(newCar));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Update the car", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(type = "string"))
    })
    @PreAuthorize(value = "#principal.getName() == authentication.name")
    @PutMapping(value = "/updateCar/{id}")
    public ResponseEntity<Object> updateCar(@RequestParam("id") Long id, Principal principal, @Valid @RequestBody CarUpdateRequest carUpdateRequest) {
        if (carService.findByUserLogin(principal.getName()).contains(carService.findByCarId(id)) == true) {
            Car updatedCar = carMapper.convertUpdateRequest(carUpdateRequest, carService.findByCarId(id));
            CarsResponse carsResponse = carMapper.toResponse(carService.update(updatedCar));
            return new ResponseEntity<>(Collections.singletonMap("cars", carsResponse), HttpStatus.OK);
        }
        else {
            throw new ForbiddenException("User can change only his cars");
        }
    }

    @Operation(summary = "Soft delete by car id", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(type = "string"))
    })
    @PreAuthorize(value = "#principal.getName() == authentication.name")
    @PatchMapping("/softDeleteByCarId/{id}")
    public ResponseEntity<Object> softDeleteByCarId(@PathVariable("id") Long id, Principal principal) {
        if (carService.findByUserLogin(principal.getName()).contains(carService.findByCarId(id)) == true) {
            Car car = carService.softDeleteByCarId(id);
            return new ResponseEntity<>(Collections.singletonMap("car", carMapper.toResponse(car)), HttpStatus.OK);
        }
        else {
            throw new ForbiddenException("User can delete only his cars");
        }
    }
}
