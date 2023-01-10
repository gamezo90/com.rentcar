package com.rentcar.controller.response;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class OrderResponse {

    private Long id;

    private Long userId;

    private Long carId;

    private LocalDate expirationDate;

}
