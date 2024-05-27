package com.marketplace.demo.Service;

import com.marketplace.demo.Model.Users;
import com.marketplace.demo.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

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
}
