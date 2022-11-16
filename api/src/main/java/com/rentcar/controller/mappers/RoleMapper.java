package com.rentcar.controller.mappers;

import com.rentcar.controller.response.RoleResponse;
import com.rentcar.domain.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {

    RoleResponse toResponse(Role role);

}