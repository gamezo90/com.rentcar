package com.rentcar.service;

import com.rentcar.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

//    Optional<User> findById(Long id);

    Optional<User> findByLogin(String login);

    User create(User user);

    void update(User user);

     User findById(Long userId);

    Long softDelete(Long userId);

    User block(Long id, Boolean isDeleted);
}