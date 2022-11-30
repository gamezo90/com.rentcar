package com.rentcar.controller.mappers;

import com.rentcar.controller.requests.CarsRequests.CarCreateRequest;
import com.rentcar.controller.requests.CarsRequests.CarUpdateRequest;
import com.rentcar.controller.requests.DiscountRequests.DiscountCreateRequest;
import com.rentcar.controller.requests.DiscountRequests.DiscountUpdateRequest;
import com.rentcar.controller.response.CarsResponse;
import com.rentcar.controller.response.DiscountResponse;
import com.rentcar.domain.Car;
import com.rentcar.domain.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
@Mapper
public interface DiscountMapper {

    DiscountResponse toResponse(Discount discount);

    Discount discountConvertCreateRequest(DiscountCreateRequest discountCreateRequest);

    Discount convertUpdateRequest(DiscountUpdateRequest discountUpdateRequest, @MappingTarget Discount discount);
}
