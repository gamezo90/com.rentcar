package com.rentcar.service;

import com.rentcar.domain.Car;
import com.rentcar.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarService {

    Car findById(Long id);

    List<Car> findAll();

    List<Car> findByUserLogin(String login);

    Car create(Car car);

    Car update(Car carToUpdate);

    Car banById(Long id);

    void checkCarWithUserLoginExist(String userLogin);

}
