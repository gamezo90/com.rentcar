package com.rentcar.service.impl;

import com.rentcar.domain.Discount;
import com.rentcar.domain.Order;
import com.rentcar.repository.DiscountRepository;
import com.rentcar.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    @Override
    public Discount findByUserId(Long id) {
        return null;
    }

    @Override
    public Discount findByUserLogin(String login) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return discountRepository.findAll();
    }

    @Override
    public Discount create(Discount discount) {
        return null;
    }

    @Override
    public Discount update(Discount discountToUpdate) {
        return null;
    }
}
