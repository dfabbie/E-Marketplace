package com.marketplace.demo.Service;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.marketplace.demo.Controller.AuthenticationController;
import com.marketplace.demo.Controller.error.BadRequestAlertException;
import com.marketplace.demo.Model.Users;
import com.marketplace.demo.Repository.UserRepo;
import com.marketplace.demo.Security.DomainUserDetailsService;
import com.marketplace.demo.Security.Jwt.JwtService;
import com.marketplace.demo.Service.dto.GlobalRecordVm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final DomainUserDetailsService domainUserDetailsService;


    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public UserService(AuthenticationManager authenticationManager, JwtService jwtService, DomainUserDetailsService domainUserDetailsService, UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.domainUserDetailsService = domainUserDetailsService;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }


    public List<Users> listUsers() {
        return userRepo.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public Users save(Users users) {
        return userRepo.save(users);
    }

    public Users addUser(Users users) {

        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepo.save(users);
    }

    public Optional<Users> loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepo.findByUsername(username);
        return user;
    }

    public GlobalRecordVm.AuthenticationVm authenticate(GlobalRecordVm.LoginVm authenticationRequest) {
        //try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            String token = jwtService.generateToken(domainUserDetailsService.loadUserByUsername(authenticationRequest.getUsername()));
            return new GlobalRecordVm.AuthenticationVm(token);
//        } catch (BadCredentialsException ex) {
//            throw new BadRequestAlertException(AuthenticationController.MESSAGE_LOGIN_INVALID, AuthenticationController.EntityNameUser, AuthenticationController.LOGIN_INVALID);
//        }
    }
}
