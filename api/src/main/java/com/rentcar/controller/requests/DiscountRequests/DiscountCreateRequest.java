package com.rentcar.controller.requests.DiscountRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
@Data
@Schema(description = "Discount create request")
public class DiscountCreateRequest {

    @Schema(defaultValue = "9", type = "Long" , description = "User id")
    @Positive
//    @Pattern(regexp = "[0-9]\\W", message = "User id must be integers")
//    @Pattern(regexp = "^[A-Za-z][\\w ]$", message = "Country name must start with letter " +
//            "and consist of A-Z, a-z, 0-9 and _ ")
    private Long userId;

    @Schema(defaultValue = "9", type = "Float" , description = "Discount size")
    @Positive
 //   @Pattern(regexp = "[0-9.]\\W", message = "User id must be integers")
    private Float discountSize;

    @Schema(defaultValue = "1672330409000", type = "Timestamp" , description = "Expiration date")
    @Past
    private Timestamp expirationDate;
}
