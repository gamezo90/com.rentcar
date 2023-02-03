package com.rentcar.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CarsResponse {

    private Long id;

    private String manufacturer;

    private String model;

    private String yearOfManufacture;

    private String engineVolume;

    private String color;

    private String conditioner;

    private String registrationNumber;

    private String price;

    private String photo;

    private String region;

}
