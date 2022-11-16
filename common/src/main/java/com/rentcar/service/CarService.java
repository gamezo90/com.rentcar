package com.rentcar.service;

import com.rentcar.domain.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {

    List<Car> findAll();

    Page<Car> findAll(Pageable pageable);

    Car create(Car item, Long subCategoryId);

    Car findById(Long itemId);

    Car update(Car object);

}
