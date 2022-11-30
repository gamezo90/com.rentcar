package com.rentcar.controller.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderResponse {

    private Long id;

    private Long userId;

    private Long carId;

    private Timestamp expirationDate;

}
