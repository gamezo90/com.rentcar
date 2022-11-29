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

    User addRoles(User user, Role role);
}