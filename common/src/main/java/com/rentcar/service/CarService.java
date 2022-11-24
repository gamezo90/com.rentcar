package com.rentcar.service;

import com.rentcar.domain.Car;
import com.rentcar.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {

    List<Car> findAll();

    Car findById(Long id);

    Car create(Car car);

    Car update(Car car);

    Car banById(Long id);
}
