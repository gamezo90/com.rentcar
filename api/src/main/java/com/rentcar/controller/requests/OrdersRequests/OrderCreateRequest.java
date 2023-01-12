package com.rentcar.controller.requests.OrdersRequests;

import com.rentcar.domain.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Schema(description = "Order create request")
public class OrderCreateRequest {

    @Schema(defaultValue = "9", type = "String" , description = "User id")
    @Pattern(regexp = "^\\d+$", message = "User id must be positive integers")
    private String userId;

    @Schema(defaultValue = "9", type = "String" , description = "Car id")
    @Pattern(regexp = "^\\d+$", message = "Car id must be positive integers")
    private String carId;

    @Schema(defaultValue = "01.08.2023", type = "String" , description = "Expiration date")
    @Pattern(regexp = "^\\d{2}\\.\\d{2}\\.\\d{4}$", message = "Expiration date pattern dd.MM.yyyy")
    private String expirationDate;

}
