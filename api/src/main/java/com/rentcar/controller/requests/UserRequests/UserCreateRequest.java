package com.rentcar.controller.requests.UserRequests;

import com.rentcar.domain.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.sql.Timestamp;

@Data
@Schema(description = "User create request")
public class UserCreateRequest {

    @Schema(description = "Name", defaultValue = "name", type = "String")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name must be letters")
    private String name;

    @Schema(description = "Surname", defaultValue = "surname", type = "String")
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[A-Za-z]+$", message = "Surname must be letters")
    private String surname;

    @Schema(description = "Region", defaultValue = "region", type = "String")
    @Pattern(regexp = "^[a-zA-Z]+[ ]?[a-zA-Z]+[ ]?[a-zA-Z]*$", message = "Region must be maximum three words")
    @Size(min = 2, max = 30)
    private String region;

    @Schema(description = "Birthday", defaultValue = "12.2.1986", type = "String")
    @Pattern(regexp = "^\\d{2}.\\d{2}\\.\\d{4}$", message = "Birthday date pattern dd.MM.yyyy")
    private String birthday;

    @Schema(description = "Gender pattern MALE or FEMALE, else set default NOT_SELECTED", defaultValue = "NOT_SELECTED", type = "String")
    private String gender;

    @Schema(description = "Login", defaultValue = "login", type = "String")
    @Pattern(regexp = "^([A-Za-z\\d][ ._-]?)+$", message = "Login must start from letter or number and do not contain repeating in a row space, ., _, -")
    @Size(min = 2, max = 20)
    private String login;

    @Schema(description = "Password", defaultValue = "password", type = "String")
    @Size(min = 8, max = 30)
    private String password;

    @Schema(description = "Email", defaultValue = "email@mail.rentcar", type = "String")
    @Email
    private String email;
}
