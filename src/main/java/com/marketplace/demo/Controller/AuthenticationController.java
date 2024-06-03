package com.marketplace.demo.Controller;

import com.marketplace.demo.Controller.error.BadRequestAlertException;
import com.marketplace.demo.Controller.error.ForbiddenAlertException;
import com.marketplace.demo.Repository.UserRepo;
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

    private final UserRepo userRepo;


    public AuthenticationController(JwtService jwtService, UserRepo userRepo) {
        this.jwtService = jwtService;
        this.userRepo = userRepo;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody LoginVm loginVm){
        if (!userRepo.findByUsername(loginVm.getUsername()).isPresent()){
            throw new BadRequestAlertException("Invalid Username", "User", "Error.Username.Invalid");
        }
        if (!isValid(loginVm)) {
            throw new ForbiddenAlertException("Can't access", "User", "Error.Authenticate.Invalid" );
        }
        return "Success";
        //return jwtService.generateToken(loginVm.getUsername());
    }

    private boolean isValid(LoginVm loginVm) {
        return loginVm.getUsername() != null && !loginVm.getUsername().isEmpty();
    }

}
