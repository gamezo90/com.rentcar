package com.rentcar.service.impl;

import com.rentcar.domain.Role;
import com.rentcar.domain.SystemRoles;
import com.rentcar.repository.RoleRepository;
import com.rentcar.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
@RequiredArgsConstructor
@Cacheable("roles")
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository ;

    @Override
    public List<Role> findAll() {
        if (roleRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException(String.format("Roles not found"));
        }
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        if (roleRepository.findRolesByUserId(userId).isEmpty()) {
            throw new EntityNotFoundException(String.format("The user with id: %d not found", userId));
        }
        return roleRepository.findRolesByUserId(userId);
    }

    @Override
    public Role findRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(() ->
                new EntityNotFoundException(String.format("The role with id: %d not found", roleId)));
    }

    @Override
    public Role findRoleByName(String roleName) {
        try {
            return  roleRepository.findByRoleName(SystemRoles.valueOf(roleName));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("The role with name: %s not found", roleName));
        }
    }

}
