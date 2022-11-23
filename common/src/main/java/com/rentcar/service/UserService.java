package com.rentcar.service;

import com.rentcar.domain.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User update(User userToUpdate);

    User findById(Long userId);

    User softDelete(Long id, Boolean isDeleted);
}