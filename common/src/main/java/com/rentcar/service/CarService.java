package com.rentcar.service;

import com.rentcar.domain.Car;
import com.rentcar.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {

    Car findById(Long id);

    List<Car> findAll();

    Car findByUserLogin(String login);

    Car create(Car car);

    Car update(Car carToUpdate);

}
