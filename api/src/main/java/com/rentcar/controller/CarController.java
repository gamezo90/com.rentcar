package com.rentcar.controller;


import com.rentcar.repository.CarRepository;
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

    private final CarRepository repository;

    @GetMapping("/findAllCars")
    public ResponseEntity<Object> findAllCars() {

        return new ResponseEntity<>(
                Collections.singletonMap("result", repository.findAll()),
                HttpStatus.OK
        );
    }

    @GetMapping("/findCarById")
    public ResponseEntity<Object> findCarById(@RequestParam("id") Long carId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findById(carId)), HttpStatus.OK);
    }

    @GetMapping("/findCarsByUserId")
    public ResponseEntity<Object> findCarsByUserId(@RequestParam("id") Long userId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findCarsByUserId(userId)), HttpStatus.OK);
    }
}
