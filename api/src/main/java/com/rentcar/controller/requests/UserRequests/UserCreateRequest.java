package com.rentcar.controller.requests.UserRequests;

import com.rentcar.domain.Gender;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
public class UserCreateRequest {

    @Size(min = 2, max = 20)
    private String userName;

    @Size(min = 2, max = 50)
    private String surname;

    private String region;

    private Timestamp birthday;

    private Gender gender;

    @Size(min = 2,max = 100)
    private String login;

    @Size(min = 2,max = 255)
    private String password;

    @Size(min = 2,max = 255)
    private String email;
}
