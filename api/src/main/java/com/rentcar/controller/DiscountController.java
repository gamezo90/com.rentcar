package com.rentcar.controller;

import com.rentcar.controller.mappers.DiscountMapper;
import com.rentcar.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/discount")
public class DiscountController {

    private final DiscountMapper discountMapper;

    private final DiscountService discountService;

    @GetMapping("/findDiscountByUserId")
    public ResponseEntity<Object> findByUserId(@RequestParam("id") Long userId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                discountService.findByUserId(userId)), HttpStatus.OK);
    }

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
    @GetMapping("/findDiscountByUserLogin")
    public ResponseEntity<Object> findByUserLogin(@RequestParam("user_login") String login) {

        return new ResponseEntity<>(Collections.singletonMap("result", discountService.findByUserLogin(login)), HttpStatus.OK);
    }

}
