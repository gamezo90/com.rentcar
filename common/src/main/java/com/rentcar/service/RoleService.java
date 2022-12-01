package com.rentcar.service;

import com.rentcar.domain.Role;
import com.rentcar.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Page<Role> findAll(Pageable page);

    List<Role> findRolesByUserId(Long roleId);

    Role findRoleById(Long roleId);

    Role findByRoleName(String roleName);

}
