package com.rentcar.controller.requests.CarsRequests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Car update request")
public class CarUpdateRequest {

    @Schema(defaultValue = "3.6", type = "String" , description = "Car engine volume")
    @Pattern(regexp = "^\\d+(?:\\.\\d+)?$", message = "Engine volume must be positive floating point numbers")
    private String engineVolume;

    @Schema(defaultValue = "Yes", type = "String" , description = "Conditioner")
    private String conditioner;

    @Schema(defaultValue = "Red", type = "String" , description = "Car color")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^([A-Za-z]([ -])?)*$", message = "Color must be string with space or dash")
    private String color;

    @Schema(defaultValue = "Some Photo", type = "String" , description = "Car photo")
    @Size(min = 2, max = 255)
    private String photo;

    @Schema(defaultValue = "Gomel", type = "String" , description = "Car region")
    @Pattern(regexp = "^[a-zA-Z]+\\s?[a-zA-Z]+\\s?[a-zA-Z]*$", message = "Region must be maximum three words")
    @Size(min = 2, max = 30)
    private String region;

    @Schema(defaultValue = "500", type = "String" , description = "Car price")
    @Pattern(regexp = "^\\d+(?:\\.\\d+)?$", message = "Price must be positive floating point numbers")
    private String price;

    @Schema(defaultValue = "9075IA", type = "String" , description = "Car registration number")
    @Pattern(regexp = "^\\d{4}\\s[A-Z]{2}-[1-7]|[A-Z]{2}\\s\\d{4}-[1-7]", message = "Registration number pattern 1234 AA-1 or AA 1234-1")
    private String registrationNumber;

}
