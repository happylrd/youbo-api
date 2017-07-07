package io.happylrd.youbo.controller;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.Collection;
import io.happylrd.youbo.model.domain.Comment;
import io.happylrd.youbo.model.domain.Favorite;
import io.happylrd.youbo.model.domain.Tweet;
import io.happylrd.youbo.model.dto.TweetFragmentDTO;
import io.happylrd.youbo.model.dto.UserDTO;
import io.happylrd.youbo.model.vo.LoginVO;
import io.happylrd.youbo.model.vo.RegisterVO;
import io.happylrd.youbo.model.vo.UserInfoVO;
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

    @PutMapping("/{id}")
    private ServerResponse<UserInfoVO> updateInfo(@PathVariable("id") Long id,
                                                  @RequestBody UserInfoVO userInfoVO) {
        return userService.updateInfo(id, userInfoVO);
    }

    @PostMapping("/{id}/tweets")
    private ServerResponse<Tweet> publishTweet(@PathVariable("id") Long id,
                                               @RequestBody List<TweetFragmentDTO> fragmentDTOs) {
        return userService.publishTweet(id, fragmentDTOs);
    }

    @GetMapping("/{id}/tweets")
    private ServerResponse<List<Tweet>> listMyTweet(@PathVariable("id") Long id) {
        return userService.listMyTweet(id);
    }

    // TODO: content style of sending and receiving will be improved later
    @PostMapping("/{id}/tweets/{tweetId}/comments")
    private ServerResponse<Comment> publishComment(@PathVariable("id") Long id,
                                                   @PathVariable("tweetId") Long tweetId,
                                                   @RequestParam("content") String content) {
        return userService.publishComment(id, tweetId, content);
    }

    @GetMapping("/{id}/comments")
    private ServerResponse<List<Comment>> listMyComment(@PathVariable("id") Long id) {
        return userService.listMyComment(id);
    }

    @PostMapping("/{id}/tweets/{tweetId}/collections")
    private ServerResponse<Collection> collectTweet(@PathVariable("id") Long id,
                                                    @PathVariable("tweetId") Long tweetId) {
        return userService.collectTweet(id, tweetId);
    }

    @GetMapping("/{id}/collections")
    private ServerResponse<List<Collection>> listMyCollection(@PathVariable("id") Long id) {
        return userService.listMyCollection(id);
    }

    @PutMapping("/{id}/collections/{collectionId}")
    private ServerResponse<Collection> cancelCollection(@PathVariable("id") Long id,
                                                        @PathVariable("collectionId") Long collectionId) {
        return userService.cancelCollection(collectionId);
    }

    @PostMapping("/{id}/tweets/{tweetId}/favorites")
    private ServerResponse<Favorite> doFavorite(@PathVariable("id") Long id,
                                                @PathVariable("tweetId") Long tweetId) {
        return userService.doFavorite(id, tweetId);
    }

    @GetMapping("/{id}/favorites")
    private ServerResponse<List<Favorite>> listMyFavorite(@PathVariable("id") Long id) {
        return userService.listMyFavorite(id);
    }

    @PutMapping("/{id}/favorites/{favoriteId}")
    private ServerResponse<Favorite> cancelFavorite(@PathVariable("id") Long id,
                                                    @PathVariable("favoriteId") Long favoriteId) {
        return userService.cancelFavorite(favoriteId);
    }
}
