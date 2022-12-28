package com.rentcar.controller;


import com.rentcar.controller.mappers.CarMapper;
import com.rentcar.controller.requests.CarsRequests.CarCreateRequest;
import com.rentcar.controller.requests.CarsRequests.CarUpdateRequest;
import com.rentcar.controller.requests.UserRequests.UserCreateRequest;
import com.rentcar.controller.requests.UserRequests.UserUpdateRequest;
import com.rentcar.controller.response.CarsResponse;
import com.rentcar.controller.response.UserResponse;
import com.rentcar.domain.Car;
import com.rentcar.domain.User;
import com.rentcar.repository.CarRepository;
import com.rentcar.service.CarService;
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
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    private final CarMapper carMapper;

    private final UserService userService;

    @GetMapping("/findCarById")
    public ResponseEntity<Object> findCarById(@RequestParam("id") Long carId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                carService.findById(carId)), HttpStatus.OK);
    }

    @GetMapping("/findAllCars")
    public ResponseEntity<Object> findAllCars() {

        return new ResponseEntity<>(
                Collections.singletonMap("result", carService.findAll()),
                HttpStatus.OK
        );
    }

    @GetMapping("/findCarByUserLogin")
    public ResponseEntity<Object> findCarUserByLogin(@RequestParam("user_login") String login) {
        carService.checkCarWithUserLoginExist(login);
        return new ResponseEntity<>(Collections.singletonMap("result", carService.findByUserLogin(login)), HttpStatus.OK);
    }

    @PostMapping("/createCar")
    public ResponseEntity<Object> addCar(@Valid @RequestBody CarCreateRequest createRequest) {
        Car newCar = carMapper.carConvertCreateRequest(createRequest);
        userService.findById(newCar.getUserId());
        CarsResponse response = carMapper.toResponse(carService.create(newCar));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateCar/{id}")
    public ResponseEntity<Object> updateCar(@RequestParam("id") Long id, @Valid @RequestBody CarUpdateRequest carUpdateRequest) {
        Car updatedCar = carMapper.convertUpdateRequest(carUpdateRequest, carService.findById(id));
        CarsResponse carsResponse = carMapper.toResponse(carService.update(updatedCar));
        return new ResponseEntity<>(Collections.singletonMap("cars", carsResponse), HttpStatus.OK);
    }

    @PatchMapping("/banCarById/{id}")
    public ResponseEntity<Object> banCarById(@PathVariable("id") Long id) {

        Car car = carService.banById(id);

        return new ResponseEntity<>(Collections.singletonMap(car, carMapper.toResponse(car)), HttpStatus.OK);
    }
}
