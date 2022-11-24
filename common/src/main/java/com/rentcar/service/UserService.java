package com.rentcar.service;

import com.rentcar.domain.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User update(User userToUpdate);

    User softDelete(String login);

    User banByLogin(String login);

    User findByLogin(String login);
}