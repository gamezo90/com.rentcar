package com.rentcar.controller.converters;

import com.rentcar.controller.requests.UserUpdateRequest;
import com.rentcar.domain.Credentials;
import com.rentcar.domain.User;
import com.rentcar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUpdateConverter extends UserBaseConverter<UserUpdateRequest, User> {

    private final UserRepository repository;

    @Override
    public User convert(UserUpdateRequest source) {

        User user = new User();

        user.setModificationDate(new Timestamp(new Date().getTime()));

        Credentials credentials = new Credentials(
                "1",
                source.getPassword(),
                source.getEmail()
        );

        user.setCredentials(credentials);

        return doUpdate(user, source);
    }
}
