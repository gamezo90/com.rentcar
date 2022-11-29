package com.rentcar.controller.requests.OrdersRequests;

import com.rentcar.domain.Gender;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@ApiModel(description = "Create order object without system info")
public class OrderCreateRequest {

    @Size(min = 2, max = 20)
    private Long userId;

    @Size(min = 2, max = 50)
    private Long carId;

    private Timestamp expirationDate;

}
