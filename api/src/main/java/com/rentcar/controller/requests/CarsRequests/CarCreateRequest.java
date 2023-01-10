package com.rentcar.controller.requests.CarsRequests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.sql.Timestamp;

@Data
@Schema(description = "Car create request")
public class CarCreateRequest {

    @Schema(defaultValue = "BMW", type = "String" , description = "Car manufacturer")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[A-Za-z]*[ -]?[A-Za-z]+$", message = "Manufacturer must be String with one space or dash, without numbers")
    private String manufacturer;

    @Schema(defaultValue = "X6", type = "String" , description = "Car model")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[^.]([A-Za-z0-9]([ -])?)*([.])?([A-Za-z0-9]([ -])?)*$", message = "Model must be String with space or dash or dot")
    private String model;

    @Schema(defaultValue = "01.08.2020", type = "String" , description = "Year of manufacture")
    @Pattern(regexp = "^\\d{2}.\\d{2}\\.\\d{4}$", message = "Year of manufacture pattern dd.MM.yyyy")
    private String yearOfManufacture;

    @Schema(defaultValue = "3.6", type = "String" , description = "Car engineVolume")
    @Pattern(regexp = "^\\d+(?:[\\.]\\d+)?$", message = "Engine volume must be positive floating point numbers")
    private String engineVolume;

    @Schema(defaultValue = "Red", type = "String" , description = "Car color")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^([A-Za-z]([ -])?)*$", message = "Color must be string with space or dash")
    private String color;

    @Schema(defaultValue = "True", type = "String" , description = "Conditioner")
    @NotNull
    private String conditioner;

    @Schema(defaultValue = "9075IA", type = "String" , description = "Car registration number")
    @Size(min = 2, max = 10)
    private String registrationNumber;

    @Schema(defaultValue = "500", type = "String" , description = "Car price")
    @Positive
    private String price;

    @Schema(defaultValue = "9", type = "String" , description = "User id")
    @Positive
    private String userId;

    @Schema(defaultValue = "Some Photo", type = "String" , description = "Car photo")
    @Size(min = 2, max = 255)
    private String photo;

    @Schema(defaultValue = "Gomel", type = "String" , description = "Car region")
    @Size(min = 2, max = 30)
    private String region;

}
