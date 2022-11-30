package com.rentcar.service.impl;

import com.rentcar.domain.Discount;
import com.rentcar.domain.Order;
import com.rentcar.exception.NoSuchEntityException;
import com.rentcar.repository.DiscountRepository;
import com.rentcar.service.DiscountSystem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountSystem {

    private final DiscountRepository discountRepository;
    @Override
    public Discount findByUserId(Long id) {
        return discountRepository.findByUserId(id);
    }

    @Override
    public Discount findByUserLogin(String login) {
        return discountRepository.findByUserCredentialsLogin(login);
    }

    @Override
    public List<Discount> findAll() {
        return discountRepository.findAll();
    }

    @Override
    public Discount create(Discount discount) {
        discount.setCreationDate(new Timestamp(new Date().getTime()));
        discount.setModificationDate(new Timestamp(new Date().getTime()));
        discountRepository.save(discount);

        return discountRepository.findById(discount.getId()).orElseThrow(IllegalArgumentException::new);

    }

    @Override
    public Discount update(Discount discountToUpdate) {
        discountToUpdate.setModificationDate(new Timestamp(new Date().getTime()));
        discountRepository.save(discountToUpdate);
        return discountRepository.findById(discountToUpdate.getId()).orElseThrow(IllegalArgumentException::new);
    }
}
