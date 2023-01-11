package com.rentcar.controller.requests.UserRequests;

import com.rentcar.domain.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.sql.Timestamp;

@Data
@Schema(description = "User create request")
public class UserCreateRequest {

    @Schema(description = "User name", defaultValue = "name", type = "String")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[A-Za-z]+$", message = "Color must be string with space or dash")
    private String name;

    @Schema(description = "User surname", defaultValue = "surname", type = "String")
    @Size(min = 2, max = 50)
    private String surname;

    @Schema(description = "User region", defaultValue = "region", type = "String")
    @Size(min = 2, max = 30)
    private String region;

    @Schema(description = "User birthday", defaultValue = "1672851614000", type = "String")
    @Past
    private Timestamp birthday;

    @Schema(description = "User gender", defaultValue = "NOT_SELECTED", type = "Gender")
    @NotNull
    private Gender gender;

    @Schema(description = "User login", defaultValue = "login", type = "String")
    @NotBlank
    private String login;

    @Schema(description = "User password", defaultValue = "password", type = "String")
    @NotBlank
    private String password;

    @Schema(description = "User email", defaultValue = "email@mail.rentcar", type = "String")
    @Email
    private String email;
}
