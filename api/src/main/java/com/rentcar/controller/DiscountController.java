package com.rentcar.controller;

import com.rentcar.controller.mappers.DiscountMapper;
import com.rentcar.controller.requests.DiscountRequests.DiscountCreateRequest;
import com.rentcar.controller.requests.DiscountRequests.DiscountUpdateRequest;
import com.rentcar.controller.response.DiscountResponse;
import com.rentcar.domain.Discount;
import com.rentcar.service.DiscountService;
import com.rentcar.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collections;

@Tag(name = "Discount controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/discount")
public class DiscountController {

    private final DiscountMapper discountMapper;

    private final DiscountService discountService;

    private final UserService userService;

    @Operation(summary = "Find discounts by user id")
    @GetMapping("/findDiscountByUserId")
    public ResponseEntity<Object> findByUserId(@RequestParam("userId") Long userId) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                discountService.findByUserId(userId)), HttpStatus.OK);
    }

    @Operation(summary = "Find all discounts", parameters = {
            @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", description = "Token", required = true,
                    schema = @Schema(defaultValue = "token", type = "string"))
    })
    @GetMapping("/findAllDiscounts")
    public ResponseEntity<Object> findAllDiscounts() {

        return new ResponseEntity<>(
                Collections.singletonMap("result", discountService.findAll()),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Adding discount")
    @PostMapping("/createDiscount")
    public ResponseEntity<Object> addDiscount(@Valid @RequestBody DiscountCreateRequest createRequest) {
        Discount newDiscount = discountMapper.discountConvertCreateRequest(createRequest);
        discountService.checkUserDiscountAlreadyExists(newDiscount.getUserId());
        userService.findById(newDiscount.getUserId());
        DiscountResponse response = discountMapper.toResponse(discountService.create(newDiscount));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Update discount")
    @PutMapping(value = "/updateDiscount/{id}")
    public ResponseEntity<Object> updateDiscount(@RequestParam("userId") Long userId, @Valid @RequestBody DiscountUpdateRequest discountUpdateRequest) {
        Discount updatedDiscount = discountMapper.convertUpdateRequest(discountUpdateRequest, discountService.findByUserId(userId));
        DiscountResponse discountResponse = discountMapper.toResponse(discountService.update(updatedDiscount));
        return new ResponseEntity<>(Collections.singletonMap("discount", discountResponse), HttpStatus.OK);
    }
    @Operation(summary = "Find discount by user login")
    @GetMapping("/findDiscountByUserLogin")
    public ResponseEntity<Object> findByUserLogin(@RequestParam("userLogin") String login) {

        return new ResponseEntity<>(Collections.singletonMap("result", discountService.findByUserLogin(login)), HttpStatus.OK);
    }

}
