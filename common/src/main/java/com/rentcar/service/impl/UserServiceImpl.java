package com.rentcar.service.impl;

import com.rentcar.domain.Role;
import com.rentcar.domain.SystemRoles;
import com.rentcar.domain.User;
import com.rentcar.repository.RoleRepository;
import com.rentcar.repository.UserRepository;
import com.rentcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User softDelete(Long id, Boolean isDeleted) {

        User user = userRepository.findById(id).get();

        user.setIsDeleted(isDeleted);
        userRepository.save(user);

        return user;
    }

    @Transactional
    @Override
    public User create(User user) {

        user.setCreationDate(new Timestamp(new Date().getTime()));
        user.setModificationDate(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);
        user.setIsBanned(false);
        user.setCreationDate(new Timestamp(new Date().getTime()));
        user.getCredentials().setPassword(passwordEncoder.encode(user.getCredentials().getPassword()));
        Role roleUser = roleRepository.findByRoleName(SystemRoles.ROLE_USER);

        user.setRoles(Set.of(roleUser));

        userRepository.save(user);

        return userRepository.findById(user.getId()).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    @Override
    public User update(User userToUpdate) {
        userToUpdate.setModificationDate(new Timestamp(new Date().getTime()));
        userToUpdate
                .getCredentials()
                .setPassword(passwordEncoder.encode(userToUpdate.getCredentials().getPassword()));
        userRepository.save(userToUpdate);
        return userRepository.findById(userToUpdate.getId()).orElseThrow(EntityNotFoundException::new);
    }

}
