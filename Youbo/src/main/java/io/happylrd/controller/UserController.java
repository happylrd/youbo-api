package io.happylrd.controller;

import io.happylrd.domain.Tweet;
import io.happylrd.repository.TweetRepository;
import io.happylrd.repository.UserRepository;
import io.happylrd.domain.Result;
import io.happylrd.domain.User;
import io.happylrd.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

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
    public Result<User> updateUser(@PathVariable("id") Integer id, User user) {
        User updatedUser = userRepository.findOne(id);
        if (StringUtils.isNotBlank(user.getPassword())) {
            updatedUser.setPassword(user.getPassword());
        }
        if (StringUtils.isNotBlank(user.getNickname())) {
            updatedUser.setNickname(user.getNickname());
        }
        if (StringUtils.isNotBlank(user.getGender())) {
            updatedUser.setGender(user.getGender());
        }
        if (StringUtils.isNotBlank(user.getDescription())) {
            updatedUser.setDescription(user.getDescription());
        }

        return ResultUtil.success(userRepository.save(updatedUser));
    }

    @DeleteMapping(value = "/users/{id}")
    public void removeUser(@PathVariable("id") Integer id) {
        userRepository.delete(id);
    }


    @GetMapping(value = "/users/{username}/tweets")
    public Result<List<Tweet>> listTweet(@PathVariable("username") String username) {
        return ResultUtil.success(tweetRepository.findByUser_Username(username));
    }
}
