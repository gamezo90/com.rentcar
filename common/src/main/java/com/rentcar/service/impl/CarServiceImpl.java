package com.rentcar.service.impl;

import com.rentcar.domain.Car;
import com.rentcar.domain.User;
import com.rentcar.exception.NoSuchEntityException;
import com.rentcar.repository.CarRepository;
import com.rentcar.repository.UserRepository;
import com.rentcar.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final UserRepository userRepository;
    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("Car with this id \"%s\" not found", id)));
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findByUserLogin(String login) {
        return null;
    //    return carRepository.findByUserCredentials_Login(login);
    }

    @Override
    public Car create(Car car) {
        return null;
    }

    @Override
    public Car update(Car carToUpdate) {
        return null;
    }

    @Override
    public Car banById(Long id) {
        return null;
    }
}
