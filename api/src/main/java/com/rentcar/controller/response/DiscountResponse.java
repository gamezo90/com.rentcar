package com.rentcar.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountResponse {

    private Long id;

    private Long userId;

    private Float discountSize;
}
