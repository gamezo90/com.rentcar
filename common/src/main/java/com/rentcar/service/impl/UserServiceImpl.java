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

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with this id %s not found", id)));
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByCredentialsLogin(login).orElseThrow(() ->
                new EntityNotFoundException(String.format("The user with login: %s not found", login)));
    }

    @Override
    public List<User> findAll() {

        if (userRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException(String.format("Users not found"));
        }
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User create(User user) {

        user.setCreationDate(new Timestamp(new Date().getTime()));
        user.setModificationDate(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);
        user.setIsBanned(false);
        user.getCredentials().setPassword(passwordEncoder.encode(user.getCredentials().getPassword()));
        addRoleToUser(user, roleRepository.findByRoleName(SystemRoles.valueOf("ROLE_USER")));
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

    //   return userRepository.findByCredentialsLogin(userToUpdate.getCredentials().getLogin()).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public User softDelete(String login) {
        User user = userRepository.findByCredentialsLogin(login).orElseThrow(() ->
                new EntityNotFoundException(String.format("The user with login: %s not found", login)));;
        user.setIsDeleted(true);
        userRepository.save(user);
        return user;
    }

    @Transactional
    public User banByLogin(String login) {
        User user = userRepository.findByCredentialsLogin(login).orElseThrow(() ->
                new EntityNotFoundException(String.format("The user with login: %s not found", login)));
        user.setIsBanned(true);
        userRepository.save(user);
        return user;
    }

    @Override
    public User addRoleToUser(User user, Role role) {
        Set<Role> roles = new HashSet<>();
        if (user.getRoles() != null) {
            roles.addAll(user.getRoles());
        }
        roles.add(role);
        user.setRoles(roles);
        role.getUsers().add(user);
        userRepository.save(user);
        return user;
    }

    @Override
    public User removeUserRole(User user, Role role) {
        role.getUsers().remove(user);
        userRepository.save(user);
        return user;
    }

}
