package com.rentcar.controller.requests.CarsRequests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.sql.Timestamp;

@Data
public class CarCreateRequest {

    @Schema(defaultValue = "BMW", type = "string" , description = "Car manufacturer")
    @Size(min = 2, max = 20)
    private String manufacturer;

    @Schema(defaultValue = "X6", type = "String" , description = "Car model")
    @Size(min = 2, max = 20)
    private String model;

    @Schema(defaultValue = "1672330409", type = "Timestamp" , description = "Car yearOfManufacture")
    @Past
    private Timestamp yearOfManufacture;

    @Schema(defaultValue = "3.6", type = "Float" , description = "Car engineVolume")
    @Positive
    private Float engineVolume;

    @Schema(defaultValue = "Red", type = "String" , description = "Car color")
    @Size(min = 2, max = 20)
    private String color;

    @Schema(defaultValue = "True", type = "Boolean" , description = "Conditioner")
    @NotNull
    private Boolean conditioner;

    @Schema(defaultValue = "9075IA", type = "String" , description = "Car registration number")
    @Size(min = 2, max = 10)
    private String registrationNumber;

    @Schema(defaultValue = "500", type = "Double" , description = "Car price")
    @Positive
    private Double price;

    @Schema(defaultValue = "9", type = "Long" , description = "User id")
    @Positive
    private Long userId;

    @Schema(defaultValue = "Some Photo", type = "String" , description = "Car photo")
    @Size(min = 2, max = 255)
    private String photo;

    @Schema(defaultValue = "Gomel", type = "String" , description = "Car region")
    @Size(min = 2, max = 30)
    private String region;

}
