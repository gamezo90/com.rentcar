package com.rentcar.controller.requests.CarsRequests;

import com.rentcar.domain.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CarUpdateRequest {

    @ApiModelProperty(required = true, allowableValues = "5", dataType = "float", notes = "engineVolume")
    @NotNull
    private Float engineVolume;

    @ApiModelProperty(required = true, allowableValues = "true", dataType = "boolean", notes = "conditioner")
    @NotNull
    private Boolean conditioner;

    @ApiModelProperty(required = true, allowableValues = "color", dataType = "string", notes = "color")
    private String color;

    @ApiModelProperty(required = true, allowableValues = "photo", dataType = "string", notes = "photo")
    private String photo;

    @ApiModelProperty(required = true, allowableValues = "region", dataType = "string", notes = "region")
    @Size(min = 2,max = 255)
    private String region;

    @ApiModelProperty(required = true, allowableValues = "price", dataType = "double", notes = "price")
    @Size(min = 2,max = 255)
    private Double price;

    @ApiModelProperty(required = true, allowableValues = "registrationNumber", dataType = "string", notes = "registrationNumber")
    @Size(min = 2,max = 255)
    private String registrationNumber;

}
