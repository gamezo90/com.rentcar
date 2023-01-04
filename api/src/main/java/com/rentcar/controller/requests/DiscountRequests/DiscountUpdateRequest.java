package com.rentcar.controller.requests.DiscountRequests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
@Data
public class DiscountUpdateRequest {

    @Schema(defaultValue = "1672330409", type = "Timestamp" , description = "Car yearOfManufacture")
    @Past
    private Timestamp expirationDate;

    @Schema(defaultValue = "9", type = "Float" , description = "Discount size")
    @Positive
    private Float discountSize;
}
