package com.rentcar.service.impl;

import com.rentcar.domain.Role;
import com.rentcar.domain.SystemRoles;
import com.rentcar.repository.RoleRepository;
import com.rentcar.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository ;

    @Override
    public Page<Role> findAll(Pageable page) {
        return roleRepository.findAll(page);
    }

    @Override
    public List<Role> findRolesByUserId(Long userId) {

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
