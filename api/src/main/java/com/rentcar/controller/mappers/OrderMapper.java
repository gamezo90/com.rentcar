package com.rentcar.controller.mappers;

import com.rentcar.controller.requests.OrdersRequests.OrderCreateRequest;
import com.rentcar.controller.requests.OrdersRequests.OrderUpdateRequest;
import com.rentcar.controller.response.OrderResponce;
import com.rentcar.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface OrderMapper {

    OrderResponce toResponse(Order order);

    Order orderConvertCreateRequest(OrderCreateRequest orderCreateRequest);

    Order convertUpdateRequest(OrderUpdateRequest orderUpdateRequest, @MappingTarget Order order);
}
