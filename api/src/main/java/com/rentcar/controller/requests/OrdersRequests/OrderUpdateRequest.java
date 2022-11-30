package com.rentcar.controller.requests.OrdersRequests;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class OrderUpdateRequest {

    private Timestamp expirationDate;
}
