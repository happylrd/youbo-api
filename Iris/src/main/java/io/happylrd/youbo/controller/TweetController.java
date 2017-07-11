package io.happylrd.youbo.controller;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.Comment;
import io.happylrd.youbo.model.domain.Tweet;
import io.happylrd.youbo.model.dto.TweetDTO;
import io.happylrd.youbo.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @GetMapping
    private ServerResponse<List<TweetDTO>> list() {
        return tweetService.listTweet();
    }

    @GetMapping("/{id}")
    private ServerResponse<Tweet> get(@PathVariable("id") Long id) {
        return tweetService.getTweet(id);
    }

    @GetMapping("/{id}/comments")
    private ServerResponse<List<Comment>> listComment(@PathVariable("id") Long id) {
        return tweetService.listComment(id);
    }
}
