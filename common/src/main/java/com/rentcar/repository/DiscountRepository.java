package com.rentcar.repository;

import com.rentcar.domain.Car;
import com.rentcar.domain.Discount;
import com.rentcar.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Discount findByUserId(Long id);

    Discount findByUserCredentialsLogin(String login);
}
