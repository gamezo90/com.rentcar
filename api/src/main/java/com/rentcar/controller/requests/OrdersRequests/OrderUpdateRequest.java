package com.rentcar.controller.requests.OrdersRequests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Past;
import java.sql.Timestamp;
@Data
@Schema(description = "Order update request")
public class OrderUpdateRequest {

    @Schema(defaultValue = "1672330409000", type = "Timestamp" , description = "Expiration date")
    @Past
    private Timestamp expirationDate;
}
