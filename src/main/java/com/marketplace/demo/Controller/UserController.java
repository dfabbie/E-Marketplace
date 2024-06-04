package com.marketplace.demo.Controller;

import com.marketplace.demo.Model.Users;
import com.marketplace.demo.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
// @RequestMapping(path = "/api/v0")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return userService.listUsers();
    }

    @GetMapping("/user/{id}")
    public Optional<Users> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/addUser", produces ="application/json")
    public Users save(@RequestBody Users users) {
        return userService.addUser(users);
    }



}
