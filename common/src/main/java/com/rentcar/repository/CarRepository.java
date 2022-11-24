package com.rentcar.repository;

import com.rentcar.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByUserCredentials_Login(String login);
}
