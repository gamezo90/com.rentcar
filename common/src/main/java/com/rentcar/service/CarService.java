package com.rentcar.service;

import com.rentcar.domain.Car;

import java.util.List;

public interface CarService {

    Car findByCarId(Long id);

    List<Car> findAll();

    List<Car> findByUserLogin(String login);

    Car create(Car car);

    Car update(Car carToUpdate);

    Car softDeleteByCarId(Long id);

    void checkCarWithUserLoginExist(String userLogin);

}
