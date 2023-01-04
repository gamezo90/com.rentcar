package com.rentcar.controller.requests.OrdersRequests;

import com.rentcar.domain.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
public class OrderCreateRequest {

    @Schema(defaultValue = "9", type = "Long" , description = "User id")
    @Positive
    private Long userId;

    @Schema(defaultValue = "9", type = "Long" , description = "Car id")
    @Positive
    private Long carId;

    @Schema(defaultValue = "1672330409000", type = "Timestamp" , description = "Expiration date")
    @Past
    private Timestamp expirationDate;

}
