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

    @Schema(defaultValue = "01.08.2023", type = "String" , description = "Expiration date")
    @Pattern(regexp = "^\\d{2}\\.\\d{2}\\.\\d{4}$", message = "Expiration date pattern dd.MM.yyyy")
    private String expirationDate;

    @Schema(defaultValue = "9", type = "String" , description = "Discount size")
    @Pattern(regexp = "^(\\d?\\d)(\\.\\d{0,2})?$|^100$", message = "User discount size must be positive floating point numbers between 0 and 100")
    private String discountSize;
}
