package com.rentcar.controller.mappers;

import com.rentcar.controller.requests.CarsRequests.CarCreateRequest;
import com.rentcar.controller.requests.CarsRequests.CarUpdateRequest;
import com.rentcar.controller.response.CarsResponse;
import com.rentcar.domain.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface CarMapper {
    CarsResponse toResponse(Car car);
    List<CarsResponse> toResponse(List<Car> car);
    @Mapping(target = "yearOfManufacture", expression =
            "java(com.rentcar.controller.utils.DateConvertUtil.parseToLocalDate(carCreateRequest.getYearOfManufacture()))")
    @Mapping(target = "conditioner", expression =
            "java(com.rentcar.controller.utils.StringToBooleanConvertUtil.parseStringToBoolean(carCreateRequest.getConditioner()))")
    Car carConvertCreateRequest(CarCreateRequest carCreateRequest);

    @Mapping(target = "conditioner", expression =
            "java(com.rentcar.controller.utils.StringToBooleanConvertUtil.parseStringToBoolean(carUpdateRequest.getConditioner()))")
    Car convertUpdateRequest(CarUpdateRequest carUpdateRequest, @MappingTarget Car car);
}
