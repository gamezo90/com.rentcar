package com.rentcar.service.impl;

import com.rentcar.domain.Role;
import com.rentcar.domain.SystemRoles;
import com.rentcar.domain.User;
import com.rentcar.repository.RoleRepository;
import com.rentcar.repository.UserRepository;
import com.rentcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
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

    public void checkUserLoginAndEmailForNotExistInDB(User user) {

        String userLogin = user.getCredentials().getLogin();
        Optional<User> userByLogin = userRepository.findByCredentialsLogin(userLogin);

        if (userByLogin.isPresent() && checkUsersIdForMismatch(userByLogin.get(), user)) {
            throw new EntityExistsException(
                    String.format("User with this login %s already exists", userLogin));
        }

        String userEmail = user.getCredentials().getEmail();
        Optional<User> userByEmail = userRepository.findByCredentialsEmail(userEmail);

        if (userByEmail.isPresent() && checkUsersIdForMismatch(userByEmail.get(), user)) {

            throw new EntityExistsException(
                    String.format("User with this email %s already exists", userEmail));
        }

    }

    private boolean checkUsersIdForMismatch(User user1, User user2) {

        return !user1.getId().equals(user2.getId());
    }

}
