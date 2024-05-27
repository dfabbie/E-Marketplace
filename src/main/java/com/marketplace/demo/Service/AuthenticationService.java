package com.marketplace.demo.Service;
import com.marketplace.demo.Dtos.LoginUserDto;
import com.marketplace.demo.Dtos.RegisterUserDto;
import com.marketplace.demo.Model.Users;
import com.marketplace.demo.Repository.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepo userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepo userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users signup(RegisterUserDto input) {
//        Users user = new Users()
//                .setFirstName(input.getFirstName())
//                .setPassword(input.getPassword());
        return userRepository.save(Users.builder().firstName(input.getFirstName()).email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword())).username(input.getUsername()).build());
    }

    public Users authenticate(LoginUserDto input) {

//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        input.getEmail(),
//                        input.getPassword()
//                )
//        );

        return (Users) userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
