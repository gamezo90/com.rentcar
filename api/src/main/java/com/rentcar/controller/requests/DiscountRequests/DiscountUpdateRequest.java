package com.rentcar.controller.requests.DiscountRequests;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class DiscountUpdateRequest {

    private Timestamp expirationDate;

    private Float discountSize;
}
