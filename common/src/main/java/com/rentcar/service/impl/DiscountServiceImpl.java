package com.rentcar.service.impl;

import com.rentcar.domain.Discount;
import com.rentcar.domain.User;
import com.rentcar.repository.DiscountRepository;
import com.rentcar.repository.UserRepository;
import com.rentcar.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    private final UserServiceImpl userService;

    private final UserRepository userRepository;

    @Override
    public Discount findByUserId(Long id) {
        if (discountRepository.findByUserId(id) == null) {
            throw new EntityNotFoundException(String.format("Discount for User with id %s not found", id));
        }
        return discountRepository.findByUserId(id);
    }


    @Override
    public Discount findByUserLogin(String login) {
        if (discountRepository.findByUserCredentialsLogin(login) == null) {
            throw new EntityNotFoundException(String.format("Discount for User with login %s not found", login));
        }
        return discountRepository.findByUserCredentialsLogin(login);
    }


    @Override
    public List<Discount> findAll() {

        if (discountRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException(String.format("Discounts not found"));
        }
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

    public void checkUserIdForNotExist(Long userId) {
        userService.findById(userId);
      }

    public void checkUserDiscountAlreadyExists(Long userId) {
        if (userRepository.findById(userId).isPresent()){
            throw new EntityExistsException(
                    String.format("User discount with this id %s already exists", userId));
        }

    }
}
