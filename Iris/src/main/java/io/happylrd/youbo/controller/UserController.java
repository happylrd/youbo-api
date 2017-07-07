package io.happylrd.youbo.controller;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.Tweet;
import io.happylrd.youbo.model.dto.TweetFragmentDTO;
import io.happylrd.youbo.model.dto.UserDTO;
import io.happylrd.youbo.model.vo.LoginVO;
import io.happylrd.youbo.model.vo.RegisterVO;
import io.happylrd.youbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    private ServerResponse<UserDTO> register(RegisterVO registerVO) {
        return userService.register(registerVO);
    }

    @PostMapping(value = "/login")
    private ServerResponse<UserDTO> login(LoginVO loginVO) {
        return userService.login(loginVO);
    }

    @GetMapping(value = "/{id}")
    private ServerResponse<UserDTO> getInfo(@PathVariable("id") Long id) {
        return userService.getInfo(id);
    }

    @PostMapping("/{id}/tweets")
    private ServerResponse<Tweet> publishTweet(@PathVariable("id") Long id, @RequestBody List<TweetFragmentDTO> fragmentDTOs) {
        return userService.publishTweet(id, fragmentDTOs);
    }

    @GetMapping("/{id}/tweets")
    private ServerResponse<List<Tweet>> listMyTweet(@PathVariable("id") Long id) {
        return userService.listMyTweet(id);
    }
}
