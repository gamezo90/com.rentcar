package com.rentcar.controller.requests.DiscountRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Schema(description = "Discount create request")
public class DiscountCreateRequest {

    @Schema(defaultValue = "9", type = "Long" , description = "User id")
    @Pattern(regexp = "^\\d+$", message = "User id must be positive integers")
    private String userId;

    @Schema(defaultValue = "9", type = "Float" , description = "Discount size")
    @Pattern(regexp = "^\\d+(?:[\\.]\\d+)?$", message = "User discount size must be positive floating point numbers")
    private String discountSize;

    @Schema(defaultValue = "01.08.2023", type = "LocalDate" , description = "Expiration date")
    @Pattern(regexp = "^\\d{2}.\\d{2}\\.\\d{4}$", message = "Expiration date pattern dd.MM.yyyy")
    private String expirationDate;

}
