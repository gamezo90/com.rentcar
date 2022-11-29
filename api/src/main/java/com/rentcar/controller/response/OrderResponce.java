package com.rentcar.controller.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
public class OrderResponce {

    private Long id;

    private Long userId;

    private Long carId;

    private Timestamp expirationDate;

}
