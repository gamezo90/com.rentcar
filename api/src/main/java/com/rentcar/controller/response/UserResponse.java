package com.rentcar.controller.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class UserResponse {

    private long id;

    private String name;

    private String surname;

    private boolean isBanned;

    private boolean isDeleted;

    private String region;

    private Timestamp birthday;

    private String gender;

    private CredentialsResponse credentials;

    private Set<RoleResponse> roles;

    private Set<CarsResponse> cars;

    private DiscountResponse discountResponse;

}




