package com.rentcar.service;

import com.rentcar.domain.Role;
import com.rentcar.domain.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    User findByLogin(String login);

    List<User> findAll();

    User create(User user);

    User update(User userToUpdate);

    User softDelete(String login);

    User banByLogin(String login);

    User addRoleToUser(User user, Role role);

    User removeUserRole(User user, Role role);
    void checkUserLoginAndEmailForNotExistInDB(User user);
}