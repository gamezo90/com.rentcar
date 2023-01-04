package com.rentcar.controller.requests.OrdersRequests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Past;
import java.sql.Timestamp;
@Data
public class OrderUpdateRequest {

    @Schema(defaultValue = "1672330409", type = "Timestamp" , description = "Car yearOfManufacture")
    @Past
    private Timestamp expirationDate;
}
