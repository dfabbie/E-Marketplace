package com.marketplace.demo.Controller;

import com.marketplace.demo.Controller.error.BadRequestAlertException;
import com.marketplace.demo.Controller.error.ForbiddenAlertException;
import com.marketplace.demo.Repository.UserRepo;
import com.marketplace.demo.Service.JwtService;
import com.marketplace.demo.Service.UserService;
import com.marketplace.demo.Service.dto.LoginVm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final JwtService jwtService;

    private final UserRepo userRepo;

    //private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;


    public AuthenticationController(JwtService jwtService, UserRepo userRepo, PasswordEncoder passwordEncoder, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody LoginVm logIn){
        if (!userRepo.findByUsername(logIn.getUsername()).isPresent()){
            throw new BadRequestAlertException("Invalid Username", "User", "Error.Username.Invalid");
        }

        UserDetails userDetails = userService.loadUserByUsername(logIn.getUsername());

        if (!passwordEncoder.matches(logIn.getPassword(), userDetails.getPassword())) {
            throw new BadRequestAlertException("Invalid Password", "User", "Error.Password.Invalid");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(logIn.getUsername(), logIn.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtService.generateToken(authentication);
    }

    private boolean isValid(LoginVm loginVm) {
        return loginVm.getUsername() != null && !loginVm.getUsername().isEmpty();
    }


}
