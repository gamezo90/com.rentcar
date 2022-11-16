package com.rentcar.controller.converters;

import com.rentcar.controller.requests.UserCreateRequest;
import com.rentcar.domain.Credentials;
import com.rentcar.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserCreateConverter extends UserBaseConverter<UserCreateRequest, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User convert(UserCreateRequest source) {

        User user = new User();

        user.setCreationDate(new Timestamp(new Date().getTime()));

        Credentials credentials = new Credentials(
                source.getLogin(),
                passwordEncoder.encode(source.getPassword()),
                source.getEmail()
        );

        user.setCredentials(credentials);

        return doConvert(user, source);
    }
}
