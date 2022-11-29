package com.rentcar.controller.requests.CarsRequests;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CarCreateRequest {

    private String manufacturer;

    private String model;

    private Integer yearOfManufacture;

    private Float engineVolume;

    private String color;

    private Boolean conditioner;

    private String registrationNumber;

    private Double price;

    private Long userId;

    private String photo;

    private String region;

}
