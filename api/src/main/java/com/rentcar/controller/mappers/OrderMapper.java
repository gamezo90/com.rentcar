package com.rentcar.controller.mappers;

import com.rentcar.controller.requests.OrdersRequests.OrderCreateRequest;
import com.rentcar.controller.requests.OrdersRequests.OrderUpdateRequest;
import com.rentcar.controller.response.OrderResponse;
import com.rentcar.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface OrderMapper {

    OrderResponse toResponse(Order order);

    @Mapping(target = "expirationDate", expression =
            "java(com.rentcar.controller.utils.DateConvertUtil.parseToLocalDate(orderCreateRequest.getExpirationDate()))")
    Order orderConvertCreateRequest(OrderCreateRequest orderCreateRequest);

    @Mapping(target = "expirationDate", expression =
            "java(com.rentcar.controller.utils.DateConvertUtil.parseToLocalDate(orderUpdateRequest.getExpirationDate()))")
    Order convertUpdateRequest(OrderUpdateRequest orderUpdateRequest, @MappingTarget Order order);
}
