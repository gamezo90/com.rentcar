package com.rentcar.controller.requests.DiscountRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
@Data
public class DiscountCreateRequest {

    @Schema(defaultValue = "9", type = "Long" , description = "User id")
    @Positive
    private Long userId;

    @Schema(defaultValue = "9", type = "Float" , description = "Discount size")
    @Positive
    private Float discountSize;

    @Schema(defaultValue = "1672330409000", type = "Timestamp" , description = "Expiration date")
    @Past
    private Timestamp expirationDate;
}
