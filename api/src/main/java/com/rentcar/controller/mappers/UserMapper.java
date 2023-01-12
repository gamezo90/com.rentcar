package com.rentcar.controller.mappers;

import com.rentcar.controller.requests.UserRequests.UserCreateRequest;
import com.rentcar.controller.requests.UserRequests.UserUpdateRequest;
import com.rentcar.controller.response.UserResponse;
import com.rentcar.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses = {RoleMapper.class})
public interface UserMapper {

    UserResponse toResponse(User user);

    @Mapping(source = "login",target = "credentials.login")
    @Mapping(source = "password",target = "credentials.password")
    @Mapping(source = "email",target = "credentials.email")
    @Mapping(target = "birthday", expression =
            "java(com.rentcar.controller.utils.DateConvertUtil.parseToLocalDate(userCreateRequest.getBirthday()))")
    @Mapping(target = "gender", expression =
            "java(com.rentcar.controller.utils.StringToGenderConvertUtil.parseStringToGender(userCreateRequest.getGender()))")
    User convertCreateRequest(UserCreateRequest userCreateRequest);

    @Mapping(source = "password",target = "credentials.password")
    @Mapping(source = "email",target = "credentials.email")
    User convertUpdateRequest(UserUpdateRequest userUpdateRequest, @MappingTarget User user);
}
