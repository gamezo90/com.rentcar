package com.rentcar.service;

import com.rentcar.domain.Order;
import com.rentcar.domain.Discount;

import java.util.List;

public interface DiscountService {

    Discount findByUserId(Long id);

    Discount findByUserLogin(String login);

    List<Discount> findAll();

    Discount create(Discount discount);

    Discount update(Discount discountToUpdate);

}