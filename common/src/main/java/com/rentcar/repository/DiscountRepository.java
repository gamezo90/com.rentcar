package com.rentcar.repository;

import com.rentcar.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Order, Long> {
}
