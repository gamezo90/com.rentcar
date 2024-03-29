package com.rentcar.controller.requests.UserRequests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.*;

@Data
@Schema(description = "User update request")
public class UserUpdateRequest {

    @Schema(description = "Name", defaultValue = "name", type = "String")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name must be letters")
    private String name;

    @Schema(description = "Surname", defaultValue = "surname", type = "String")
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[A-Za-z]+$", message = "Surname must be letters")
    private String surname;

    @Schema(description = "Region", defaultValue = "region", type = "String")
    @Pattern(regexp = "^[a-zA-Z]+\\s?[a-zA-Z]+\\s?[a-zA-Z]*$", message = "Region must be maximum three words")
    @Size(min = 2, max = 30)
    private String region;

    @Schema(description = "Password", defaultValue = "password", type = "String")
    @Size(min = 8, max = 30)
    @Pattern(regexp = "^\\S*[A-Z]\\S*$", message = "Password must contain at least one uppercase letter")
    private String password;

    @Schema(description = "Email", defaultValue = "email@mail.rentcar", type = "String")
    @Email
    private String email;

}
