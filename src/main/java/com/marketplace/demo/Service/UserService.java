package com.marketplace.demo.Service;

import com.marketplace.demo.Model.Users;
import com.marketplace.demo.Repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
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

    public void addUser(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userRepo.save(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepo.findByUsername(username);
        return user.map(Users::new)
                .orElseThrow(() -> new UsernameNotFoundException("UserName not found: " + username));
    }
}
