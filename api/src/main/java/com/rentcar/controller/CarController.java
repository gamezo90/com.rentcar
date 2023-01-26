package com.rentcar.controller;


import com.rentcar.controller.mappers.CarMapper;
import com.rentcar.controller.requests.CarsRequests.CarCreateRequest;
import com.rentcar.controller.requests.CarsRequests.CarUpdateRequest;
import com.rentcar.controller.response.CarsResponse;
import com.rentcar.domain.Car;
import com.rentcar.service.CarService;
import com.rentcar.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@Tag(name = "Car controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    private final CarMapper carMapper;

    private final UserService userService;

    @Operation(summary = "Find car by car id")
    @GetMapping("/findCarByCarId")
    public ResponseEntity<Object> findCarByCarId(@RequestParam("id") Long carId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                carService.findByCarId(carId)), HttpStatus.OK);
    }

    @Operation(summary = "Find all cars")
    @GetMapping("/findAllCars")
    public ResponseEntity<Object> findAllCars() {

        return new ResponseEntity<>(
                Collections.singletonMap("result", carService.findAll()),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Find cars by user login")
    @GetMapping("/findCarsByUserLogin")
    public ResponseEntity<Object> findCarsByUserLogin(@RequestParam("user_login") String login) {
        carService.checkCarWithUserLoginExist(login);
        return new ResponseEntity<>(Collections.singletonMap("result", carService.findByUserLogin(login)), HttpStatus.OK);
    }

    @Operation(summary = "Adding a car")
    @PostMapping("/createCar")
    public ResponseEntity<Object> addCar(@Valid @RequestBody CarCreateRequest createRequest) {
        Car newCar = carMapper.carConvertCreateRequest(createRequest);
        userService.findById(newCar.getUserId());
        CarsResponse response = carMapper.toResponse(carService.create(newCar));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Update the car")
    @PutMapping(value = "/updateCar/{id}")
    public ResponseEntity<Object> updateCar(@RequestParam("id") Long id, @Valid @RequestBody CarUpdateRequest carUpdateRequest) {
        Car updatedCar = carMapper.convertUpdateRequest(carUpdateRequest, carService.findByCarId(id));
        CarsResponse carsResponse = carMapper.toResponse(carService.update(updatedCar));
        return new ResponseEntity<>(Collections.singletonMap("cars", carsResponse), HttpStatus.OK);
    }

    @Operation(summary = "Ban car by car id")
    @PatchMapping("/banCarByCarId/{id}")
    public ResponseEntity<Object> banCarByCarId(@PathVariable("id") Long id) {

        Car car = carService.banByCarId(id);

        return new ResponseEntity<>(Collections.singletonMap(car, carMapper.toResponse(car)), HttpStatus.OK);
    }
}
