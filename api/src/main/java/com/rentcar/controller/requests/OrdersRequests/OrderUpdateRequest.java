package com.rentcar.controller.requests.OrdersRequests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.Pattern;

@Data
@Schema(description = "Order update request")
public class OrderUpdateRequest {

    @Schema(defaultValue = "01.08.2023", type = "String" , description = "Expiration date")
    @Pattern(regexp = "^\\d{2}\\.\\d{2}\\.\\d{4}$", message = "Expiration date pattern dd.MM.yyyy")
    private String expirationDate;
}
