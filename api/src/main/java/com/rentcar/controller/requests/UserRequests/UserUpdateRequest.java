package com.rentcar.controller.requests.UserRequests;

import com.rentcar.domain.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Schema(description = "User update request")
public class UserUpdateRequest {

    @Schema(description = "User name", defaultValue = "name", type = "string")
    @Size(min = 2, max = 20)
    private String name;

    @Schema(description = "User surname", defaultValue = "surname", type = "string")
    @Size(min = 2, max = 50)
    private String surname;

    @Schema(description = "User region", defaultValue = "region", type = "string")
    @Size(min = 2, max = 30)
    private String region;

    @Schema(description = "User gender", defaultValue = "NOT_SELECTED", type = "Gender")
    @NotNull
    private Gender gender;

    @Schema(description = "User password", defaultValue = "password", type = "String")
    @NotBlank
    @Size(min = 2,max = 255)
    private String password;

    @Schema(description = "User email", defaultValue = "email@mail.rentcar", type = "String")
    @Email
    private String email;

}
