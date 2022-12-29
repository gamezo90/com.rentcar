package com.rentcar.controller.requests.CarsRequests;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CarUpdateRequest {

    @NotNull
    private Float engineVolume;

    @NotNull
    private Boolean conditioner;

    private String color;

    private String photo;

    @Size(min = 2,max = 255)
    private String region;

    @Size(min = 2,max = 255)
    private Double price;

    @Size(min = 2,max = 255)
    private String registrationNumber;

}
