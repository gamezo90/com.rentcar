package com.rentcar.controller.requests.CarsRequests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class CarUpdateRequest {

    @Schema(defaultValue = "3.6", type = "Float" , description = "Car engineVolume")
    @Positive
    private Float engineVolume;

    @Schema(defaultValue = "True", type = "Boolean" , description = "Car conditioner")
    @NotNull
    private Boolean conditioner;

    @Schema(defaultValue = "Red", type = "String" , description = "Car color")
    @Size(min = 1,max = 20)
    private String color;

    @Schema(defaultValue = "Some Photo", type = "String" , description = "Car photo")
    @Size(min = 1,max = 255)
    private String photo;

    @Schema(defaultValue = "Gomel", type = "String" , description = "Car region")
    @Size(min = 1,max = 30)
    private String region;

    @Schema(defaultValue = "500", type = "Double" , description = "Car price")
    @Positive
    private Double price;

    @Schema(defaultValue = "9075IA", type = "String" , description = "Car registrationNumber")
    @Size(min = 1,max = 10)
    private String registrationNumber;

}
