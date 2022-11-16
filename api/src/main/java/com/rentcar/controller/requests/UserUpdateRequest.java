package com.rentcar.controller.requests;

import com.rentcar.domain.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
public class UserUpdateRequest {

    @ApiModelProperty(required = true, allowableValues = "sla8565vka", dataType = "long", notes = "user's id")
    @NotNull
    private Long id;

    @ApiModelProperty(required = true, allowableValues = "slavka", dataType = "string", notes = "user's login")
    @NotNull
    @Size(min = 2, max = 20)
    private String userName;

    @ApiModelProperty(required = true, allowableValues = "kalevich", dataType = "string", notes = "user's surname")
    @NotNull
    @Size(min = 2, max = 50)
    private String surname;

    @ApiModelProperty(required = true, allowableValues = "hgfdhdcghd", dataType = "string", notes = "user's region")
    private String region;

    @ApiModelProperty(required = true, allowableValues = "MALE, FEMALE, NOT_SELECTED", dataType = "string", notes = "user's gender")
    private Gender gender;

    @ApiModelProperty(required = true, allowableValues = "", dataType = "string", notes = "user's password")
    @Size(min = 2,max = 255)
    private String password;

    @ApiModelProperty(required = true, allowableValues = "", dataType = "string", notes = "user's email")
    @Size(min = 2,max = 255)
    private String email;

}
