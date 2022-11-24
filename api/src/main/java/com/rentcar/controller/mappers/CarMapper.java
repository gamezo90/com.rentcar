package com.rentcar.controller.mappers;

import com.rentcar.controller.requests.CarsRequests.CarCreateRequest;
import com.rentcar.controller.requests.UserRequests.UserCreateRequest;
import com.rentcar.controller.response.CarsResponse;
import com.rentcar.controller.response.UserResponse;
import com.rentcar.domain.Car;
import com.rentcar.domain.User;
import org.mapstruct.Mapper;

@Mapper
public interface CarMapper {
    CarsResponse toResponse(Car car);

    Car carConvertCreateRequest(CarCreateRequest carCreateRequest);

}
