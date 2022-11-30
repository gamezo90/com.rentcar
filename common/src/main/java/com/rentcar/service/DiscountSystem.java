package com.rentcar.service;

import com.rentcar.domain.Role;
import com.rentcar.domain.Discount;

import java.util.List;

public interface DiscountSystem {

    Discount findByUserId(Long id);

    Discount findByUserLogin(String login);

    List<Discount> findAll();

    Discount create(Discount discount);

    Discount update(Discount discountToUpdate);

}
