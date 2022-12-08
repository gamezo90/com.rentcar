package com.rentcar.service;

import com.rentcar.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    List<Role> findRolesByUserId(Long userId);

    Role findRoleById(Long roleId);

    Role findRoleByName(String roleName);

}
