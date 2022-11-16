package com.rentcar.service.impl;

import com.rentcar.domain.Car;
import com.rentcar.repository.CarRepository;
import com.rentcar.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository itemRepository;

    @Override
    public List<Car> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Page<Car> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Car create(Car car, Long subCategoryId) {
//        car.setSubCategory(subCategoryRepository.findById(subCategoryId).orElseThrow(EntityNotFoundException::new));
        return itemRepository.save(car);
    }

    @Override
    public Car findById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public Car update(Car object) {
        return itemRepository.save(object);
    }
}
