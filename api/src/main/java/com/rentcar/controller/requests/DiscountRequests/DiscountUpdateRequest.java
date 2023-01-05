package com.rentcar.controller.requests.DiscountRequests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
@Data
@Schema(description = "Discount update request")
public class DiscountUpdateRequest {

    @Schema(defaultValue = "1672330409000", type = "Timestamp" , description = "Expiration date")
   @Future
//    @Pattern(regexp = "^\\d{2}.\\d{2}\\.\\d{4}$",
//            message = "User birth pattern dd.MM.yyyy and must be later 1900 ")
    private Timestamp expirationDate;

    @Schema(defaultValue = "9", type = "Float" , description = "Discount size")
    @Pattern(regexp = "^\\d+(?:[\\.]\\d+)?$", message = "User discount size must be positive floating point numbers")
    private String discountSize;
}
