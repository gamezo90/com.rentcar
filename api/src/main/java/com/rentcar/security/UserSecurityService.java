package com.rentcar.security;

import com.rentcar.domain.Role;
import com.rentcar.domain.User;
import com.rentcar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> searchResult = userRepository.findByCredentialsLogin(username);

        if (searchResult.isPresent()) {

            User user = searchResult.get();

            return new org.springframework.security.core.userdetails.User(
                    user.getCredentials().getLogin(),
                    user.getCredentials().getPassword(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList(
                            user.getRoles()
                                    .stream()
                                    .map(Role::getRoleName)
                                    .collect(Collectors.joining(","))
                    )
            );
        } else {
            throw new UsernameNotFoundException(String.format("User with login \"%s\" not found", username));
        }
    }
}
