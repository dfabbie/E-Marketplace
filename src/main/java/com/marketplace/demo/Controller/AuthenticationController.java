package com.marketplace.demo.Controller;

import com.marketplace.demo.Controller.error.BadRequestAlertException;
import com.marketplace.demo.Controller.error.ForbiddenAlertException;
import com.marketplace.demo.Repository.UserRepo;
import com.marketplace.demo.Service.JwtService;
import com.marketplace.demo.Service.dto.LoginVm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    private final AuthenticationManager authenticationManager;


    public AuthenticationController(JwtService jwtService, UserRepo userRepo, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody LoginVm logIn){
       Authentication authenticate =  authenticationManager.authenticate(new
               UsernamePasswordAuthenticationToken(logIn.getUsername(), logIn.getPassword()));


        if (!userRepo.findByUsername(logIn.getUsername()).isPresent()){
            throw new BadRequestAlertException("Invalid Username", "User", "Error.Username.Invalid");
        }
//        if (!isValid(logIn)) {
//            throw new ForbiddenAlertException("Can't access", "User", "Error.Authenticate.Invalid" );
//        }
        return jwtService.generateToken(logIn.getUsername());
    }

    private boolean isValid(LoginVm loginVm) {
        return loginVm.getUsername() != null && !loginVm.getUsername().isEmpty();
    }




}
