package com.marketplace.demo.Controller;

import com.marketplace.demo.Controller.error.BadRequestAlertException;
import com.marketplace.demo.Repository.UserRepo;
import com.marketplace.demo.Security.DomainUserDetailsService;
import com.marketplace.demo.Security.Jwt.JwtService;
import com.marketplace.demo.Service.UserService;
import com.marketplace.demo.Service.dto.GlobalRecordVm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    public static final String EntityNameUser = "User";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String MESSAGE_LOGIN_INVALID = "LOGIN FAILED";
    public static final String LOGIN_INVALID = "INVALID LOGIN";


    private final UserRepo userRepo;

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private DomainUserDetailsService domainUserDetailsService;
    private final JwtService jwtService;

    public AuthenticationController(UserRepo userRepo, AuthenticationManager authenticationManager, UserService userService, JwtService jwtService) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<GlobalRecordVm.AuthenticationVm> generateToken(@RequestBody @Valid final GlobalRecordVm.LoginVm authenticationRequest) {

            GlobalRecordVm.AuthenticationVm authenticationVm = userService.authenticate(authenticationRequest);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(AUTHORIZATION_HEADER, "Bearer " + authenticationVm.getAccessToken());
            return new ResponseEntity<>(authenticationVm, httpHeaders, HttpStatus.OK);
//        } catch (BadCredentialsException ex) {
//            throw new BadRequestAlertException(MESSAGE_LOGIN_INVALID, EntityNameUser, LOGIN_INVALID);
//        }
    }


}
