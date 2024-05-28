package com.marketplace.demo.Controller;

import com.marketplace.demo.Service.JwtService;
import com.marketplace.demo.Service.dto.LoginVm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final JwtService jwtService;

    public AuthenticationController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody LoginVm loginVm){
        return jwtService.generateToken(loginVm.getUsername());
    }



}
