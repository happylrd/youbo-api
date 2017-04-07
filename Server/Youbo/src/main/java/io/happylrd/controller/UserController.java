package io.happylrd.controller;

import io.happylrd.repository.UserRepository;
import io.happylrd.domain.Result;
import io.happylrd.domain.User;
import io.happylrd.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping(value = "/users")
    public Result<List<User>> listUser() {
        return ResultUtil.success(userRepository.findAll());
    }

    @PostMapping(value = "/users")
    public Result<User> insertUser(User user) {
        return ResultUtil.success(userRepository.save(user));
    }

    @GetMapping(value = "/users/{username}")
    public Result<User> getUser(@PathVariable("username") String username) {
        return ResultUtil.success(userRepository.findByUsername(username));
    }

    @PutMapping(value = "/users/{id}")
    public User updateUser(@PathVariable("id") Integer id, User user) {
        return userRepository.save(user);
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        userRepository.delete(id);
    }
}
