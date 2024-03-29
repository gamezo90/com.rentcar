package com.rentcar.service;

import com.rentcar.domain.Order;
import com.rentcar.domain.Discount;
import com.rentcar.domain.User;

import java.util.List;

public interface DiscountService {

    Discount findByUserId(Long id);

    Discount findByUserLogin(String login);

    List<Discount> findAll();

    Discount create(Discount discount);

    Discount update(Discount discountToUpdate);

    void checkUserDiscountAlreadyExists(Long userId);

    void automaticDiscountAddition(String userLogin);

    Double findUserDiscountSize(String userLogin);

}
