package io.happylrd.controller;

import io.happylrd.domain.Result;
import io.happylrd.domain.Tweet;
import io.happylrd.repository.TweetRepository;
import io.happylrd.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping(value = "/tweets")
    public Result<List<Tweet>> listTweet() {
        return ResultUtil.success(tweetRepository.findAll());
    }

    @PostMapping(value = "/tweets")
    public Result<Tweet> insertTweet(Tweet tweet) {
        return ResultUtil.success(tweetRepository.save(tweet));
    }

    @GetMapping(value = "/tweets/{id}")
    public Result<Tweet> getTweet(@PathVariable("id") Integer id) {
        return ResultUtil.success(tweetRepository.findOne(id));
    }

    @PutMapping(value = "/tweets/{id}")
    public Result<Tweet> updateTweet(@PathVariable("id") Integer id, Tweet tweet) {
        return ResultUtil.success(tweetRepository.save(tweet));
    }

    @DeleteMapping(value = "/tweets/{id}")
    public void removeTweet(@PathVariable("id") Integer id) {
        tweetRepository.delete(id);
    }
}
