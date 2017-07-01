package io.happylrd.youbo.controller;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.domain.Tweet;
import io.happylrd.youbo.domain.User;
import io.happylrd.youbo.repository.TweetRepository;
import io.happylrd.youbo.repository.UserRepository;
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
    public ServerResponse<List<User>> list() {
        return ServerResponse.createBySuccess(userRepository.findAll());
    }

    @PostMapping(value = "/users")
    public ServerResponse<User> save(User user) {
        return ServerResponse.createBySuccess(userRepository.save(user));
    }

    @GetMapping(value = "/users/{username}")
    public ServerResponse<User> getByUsername(@PathVariable("username") String username) {
        return ServerResponse.createBySuccess(userRepository.findByUsername(username));
    }

    @PutMapping(value = "/users/{id}")
    public ServerResponse<User> update(@PathVariable("id") Long id,
                                       @RequestBody User user) {
        return ServerResponse.createBySuccess(userRepository.save(user));
    }

    @DeleteMapping(value = "/users/{id}")
    public void remove(@PathVariable("id") Long id) {
        userRepository.delete(id);
    }

    @GetMapping(value = "/users/{username}/tweets")
    public ServerResponse<List<Tweet>> listTweet(@PathVariable("username") String username) {
        return ServerResponse.createBySuccess(
                tweetRepository.findByUser_Username(username));
    }
}
