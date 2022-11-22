package com.rentcar.controller.mappers;

import com.rentcar.controller.requests.UserCreateRequest;
import com.rentcar.controller.response.UserResponse;
import com.rentcar.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {RoleMapper.class})
public interface UserMapper {
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "cars", target = "cars")
    UserResponse toResponse(User user);

    @Mapping(source = "login",target = "credentials.login")
    @Mapping(source = "password",target = "credentials.password")
    @Mapping(source = "email",target = "credentials.email")
    User convertCreateRequest(UserCreateRequest userCreateRequest);
}
