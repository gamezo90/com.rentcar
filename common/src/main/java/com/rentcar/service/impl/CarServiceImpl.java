package com.rentcar.service.impl;

import com.rentcar.domain.Car;
import com.rentcar.domain.User;
import com.rentcar.repository.CarRepository;
import com.rentcar.service.CarService;
import com.rentcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.rentcar.service.impl.DiscountServiceImpl.localDate;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public Car findByCarId(Long id) {
        return carRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Car with this id %s not found", id)));
    }

    @Override
    public List<Car> findAll() {
        if (carRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException(String.format("Cars not found"));
        }
        return carRepository.findAll();
    }

    @Override
    public List<Car> findByUserLogin(String login) {
        return carRepository.findByUserCredentialsLogin(login);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 100, rollbackFor = Exception.class)
    @Override
    public Car create(Car car) {

        car.setCreationDate(new Timestamp(new Date().getTime()));
        car.setModificationDate(new Timestamp(new Date().getTime()));
        car.setIsBanned(false);
        if(car.getYearOfManufacture().isAfter(localDate)) {
            throw new IllegalArgumentException(
                    String.format("Year of manufacture must be past"));
        }
        carRepository.save(car);
        return carRepository.findById(car.getId()).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 100, rollbackFor = Exception.class)
    @Override
    public Car update(Car carToUpdate) {

        carToUpdate.setModificationDate(new Timestamp(new Date().getTime()));
        if(carToUpdate.getYearOfManufacture().isAfter(localDate)) {
            throw new IllegalArgumentException(
                    String.format("Year of manufacture must be past"));
        }
        carRepository.save(carToUpdate);
        return carRepository.findById(carToUpdate.getId()).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 100, rollbackFor = Exception.class)
    @Override
    public Car softDeleteByCarId(Long id) {

        Car car = carRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Car with this id %s not found", id)));
        car.setIsBanned(true);
        carRepository.save(car);
        return car;
    }

    public void checkCarWithUserLoginExist(String userLogin) {
        if (carRepository.findByUserCredentialsLogin(userLogin).isEmpty()) {
            throw new EntityNotFoundException(
                    String.format("Car with userLogin %s not found", userLogin));
        }
    }

}
