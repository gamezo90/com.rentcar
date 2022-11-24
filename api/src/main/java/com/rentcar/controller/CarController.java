package com.rentcar.controller;


import com.rentcar.repository.CarRepository;
import com.rentcar.service.CarService;
import com.rentcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

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

}
