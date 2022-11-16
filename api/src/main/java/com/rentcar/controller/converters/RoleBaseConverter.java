package com.rentcar.controller.converters;

import com.rentcar.controller.requests.RoleRequest;
import com.rentcar.domain.Role;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class RoleBaseConverter implements Converter<RoleRequest, Role> {
    @Override
    public Role convert(RoleRequest source) {
        System.out.println("In Hibernate role converter");
        return new Role();
    }
}
