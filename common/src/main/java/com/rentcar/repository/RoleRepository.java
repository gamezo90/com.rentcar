package com.rentcar.repository;


import com.rentcar.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select r from Role r inner join r.users u where u.id = :userId")
    List<Role> findRolesByUserId(Long userId);

    Role findByRoleName(String roleName);

}
