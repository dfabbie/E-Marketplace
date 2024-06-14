package com.marketplace.demo.Security;
import com.marketplace.demo.Model.Authority;
import com.marketplace.demo.Model.Users;
import com.marketplace.demo.Repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Component("userDetailsService")
@Slf4j
public class DomainUserDetailsService implements UserDetailsService {
    private final UserRepo userRepository;

    public DomainUserDetailsService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

//        if (new EmailValidator().isValid(login, null)) {
//            return userRepository
//                    .findOneByEmailIgnoreCase(login)
//                    .map(user -> createSpringSecurityUser(login, user))
//                    .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
//        }

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        return userRepository
                .findByUsername(lowercaseLogin)
                .map(user -> createSpringSecurityUser(lowercaseLogin, user))
                .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, Users user) {
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities()
                .stream()
                .map(Authority::getAuthorityName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
