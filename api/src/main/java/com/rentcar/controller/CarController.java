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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

    @Operation(
            summary = "Adding a car",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Car adding successfully",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CarsResponse.class))),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Car not adding, Conflict",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Car not adding, Illegal Arguments",
                            content = @Content)
            })
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
