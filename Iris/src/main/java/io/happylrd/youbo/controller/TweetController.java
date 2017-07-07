package io.happylrd.youbo.controller;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.Tweet;
import io.happylrd.youbo.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @GetMapping
    private ServerResponse<List<Tweet>> list() {
        return tweetService.listTweet();
    }

    @GetMapping("/{id}")
    private ServerResponse<Tweet> get(@PathVariable("id") Long id) {
        return tweetService.getTweet(id);
    }
}
