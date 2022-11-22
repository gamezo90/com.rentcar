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
    public User findById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(
                        () ->
                                new EntityNotFoundException(
                                        String.format("The user with id:%d not found", userId)));
    }

    @Override
    public Long softDelete(Long userId) {
        User toUpdate =
                userRepository
                        .findById(userId)
                        .orElseThrow(
                                () ->
                                        new EntityNotFoundException(
                                                String.format("The user with id: %d not found", userId)));
        toUpdate.setIsDeleted(true);
        userRepository.save(toUpdate);
        return userId;
    }

    @Transactional
    @Override
    public User block(Long id, Boolean isDeleted) {

        User user = findById(id);

        user.setIsDeleted(isDeleted);
        userRepository.save(user);

        return user;
    }


    @Override
    public List<User> findAll() {return userRepository.findAllByOrderById();}

//    @Override
//    public Optional<User> findById(Long id) {
//        return userRepository.findById(id);
////                .orElseThrow(()->
////                new NoSuchEntityException(String.format("User with this id \"%s\" not found", id)));
//    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByCredentialsLogin(login);
//                .orElseThrow(() ->
//                new NoSuchEntityException(String.format("User with this login \"%s\" not found", login)));
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
    public void update(User user) {

        if (checkUserLoginAndEmailForNotExistInDB(user)) {

            userRepository.save(user);
        }
    }


    private boolean checkUserLoginAndEmailForNotExistInDB(User user) {

        String userLogin = user.getCredentials().getLogin();
        Optional<User> userByLogin = userRepository.findByCredentialsLogin(userLogin);

//        if (userByLogin.isPresent() && checkUsersIdForMismatch(userByLogin.get(), user)) {
//            throw new EntityAlreadyExsistException(
//                    String.format("User with this login \"%s\" already exists", userLogin));
//        }

        String userEmail = user.getCredentials().getEmail();
        Optional<User> userByEmail = userRepository.findByCredentialsEmail(userEmail);

//        if (userByEmail.isPresent() && checkUsersIdForMismatch(userByEmail.get(), user)) {
//
//            throw new EntityAlreadyExsistException(
//                    String.format("User with this email \"%s\" already exists", userEmail));
//        }

        return true;
    }

    private boolean checkUsersIdForMismatch(User user1, User user2) {

        return !user1.getId().equals(user2.getId());
    }
}
