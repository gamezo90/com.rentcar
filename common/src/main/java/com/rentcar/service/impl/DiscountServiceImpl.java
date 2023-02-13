package com.rentcar.service.impl;

import com.rentcar.domain.Discount;
import com.rentcar.domain.User;
import com.rentcar.repository.DiscountRepository;
import com.rentcar.repository.UserRepository;
import com.rentcar.service.DiscountService;
import com.rentcar.service.OrderService;
import com.rentcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    private final OrderService orderService;

    private final UserService userService;

    public static final LocalDate localDate = LocalDate.now();

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

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 100, rollbackFor = Exception.class)
    @Override
    public Discount create(Discount discount) {
        discount.setCreationDate(new Timestamp(new Date().getTime()));
        discount.setModificationDate(new Timestamp(new Date().getTime()));
        if(discount.getExpirationDate().isBefore(localDate)) {
            throw new IllegalArgumentException(
                    String.format("Expiration date must be future"));
        }

        discountRepository.save(discount);

        return discountRepository.findById(discount.getId()).orElseThrow(IllegalArgumentException::new);

    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 100, rollbackFor = Exception.class)
    @Override
    public Discount update(Discount discountToUpdate) {
        discountToUpdate.setModificationDate(new Timestamp(new Date().getTime()));
        if(discountToUpdate.getExpirationDate().isBefore(localDate)) {
            throw new IllegalArgumentException(
                    String.format("Expiration date must be future"));
        }
        discountRepository.save(discountToUpdate);
        return discountRepository.findById(discountToUpdate.getId()).orElseThrow(IllegalArgumentException::new);
    }

    public void checkUserDiscountAlreadyExists(Long userId) {
        if (Optional.ofNullable(discountRepository.findByUserId(userId)).isPresent()){
            throw new EntityExistsException(
                    String.format("User discount with this id %s already exists", userId));
        }
    }

    public void automaticDiscountAddition(String login) {
        if (orderService.findByUserLogin(login).size() == 5) {
            Discount newDiscount = new Discount();
            newDiscount.setDiscountSize(5d);
            newDiscount.setExpirationDate(LocalDate.now().plusYears(1));
            newDiscount.setUserId(userService.findByLogin(login).getId());
            create(newDiscount);
        }
        if (orderService.findByUserLogin(login).size() > 5 && orderService.findByUserLogin(login).size() <= 10) {
            Discount discountForUpdate = findByUserLogin(login);
            discountForUpdate.setDiscountSize(Double.valueOf(orderService.findByUserLogin(login).size()));
            discountForUpdate.setExpirationDate(LocalDate.now().plusYears(1));
            discountForUpdate.setUserId(userService.findByLogin(login).getId());
            update(discountForUpdate);
        }
    }
}
