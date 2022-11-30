package com.rentcar.controller;

import com.rentcar.controller.mappers.DiscountMapper;
import com.rentcar.controller.requests.CarsRequests.CarCreateRequest;
import com.rentcar.controller.requests.CarsRequests.CarUpdateRequest;
import com.rentcar.controller.requests.DiscountRequests.DiscountCreateRequest;
import com.rentcar.controller.requests.DiscountRequests.DiscountUpdateRequest;
import com.rentcar.controller.response.CarsResponse;
import com.rentcar.controller.response.DiscountResponse;
import com.rentcar.domain.Car;
import com.rentcar.domain.Discount;
import com.rentcar.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/discount")
public class DiscountController {

    private final DiscountMapper discountMapper;

    private final DiscountService discountService;

    @GetMapping("/findAllDiscounts")
    public ResponseEntity<Object> findAllDiscounts() {

        return new ResponseEntity<>(
                Collections.singletonMap("result", discountService.findAll()),
                HttpStatus.OK
        );
    }

    @PostMapping("/createDiscount")
    @Transactional
    public ResponseEntity<Object> addDiscount(@Valid @RequestBody DiscountCreateRequest createRequest) {
        Discount newDiscount = discountMapper.discountConvertCreateRequest(createRequest);
        DiscountResponse response = discountMapper.toResponse(discountService.create(newDiscount));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateDiscount/{id}")
    public ResponseEntity<Object> updateDiscount(@RequestParam("id") Long id, @Valid @RequestBody DiscountUpdateRequest discountUpdateRequest) {
        Discount updatedDiscount = discountMapper.convertUpdateRequest(discountUpdateRequest, discountService.findByUserId(id));
        DiscountResponse discountResponse = discountMapper.toResponse(discountService.update(updatedDiscount));
        return new ResponseEntity<>(Collections.singletonMap("discount", discountResponse), HttpStatus.OK);
    }
}
