package com.rentcar.service;

import com.rentcar.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User create(User user);

    User update(User userToUpdate);

    User softDelete(Long id, Boolean isDeleted);

    User softDelete1(String login);
}