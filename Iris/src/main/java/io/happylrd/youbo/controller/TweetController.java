package io.happylrd.youbo.controller;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.domain.Comment;
import io.happylrd.youbo.domain.Tweet;
import io.happylrd.youbo.repository.CommentRepository;
import io.happylrd.youbo.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(value = "/tweets")
    public ServerResponse<List<Tweet>> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return ServerResponse.createBySuccess(tweetRepository.findAll(sort));
    }

    @PostMapping(value = "/tweets")
    public ServerResponse<Tweet> save(Tweet tweet) {
        return ServerResponse.createBySuccess(tweetRepository.save(tweet));
    }

    @GetMapping(value = "/tweets/{id}")
    public ServerResponse<Tweet> get(@PathVariable("id") Long id) {
        return ServerResponse.createBySuccess(tweetRepository.findOne(id));
    }

    @PutMapping(value = "/tweets/{id}")
    public ServerResponse<Tweet> update(@PathVariable("id") Long id,
                                        @RequestBody Tweet tweet) {
        return ServerResponse.createBySuccess(tweetRepository.save(tweet));
    }

    @DeleteMapping(value = "/tweets/{id}")
    public void remove(@PathVariable("id") Long id) {
        tweetRepository.delete(id);
    }

    @GetMapping(value = "/tweets/search")
    public ServerResponse<List<Tweet>> listByContent(@RequestParam(value = "content") String content) {
        return ServerResponse.createBySuccess(
                tweetRepository.findByContentLikeIgnoreCase("%" + content + "%"));
    }

    @GetMapping(value = "/tweets/{id}/comments")
    public ServerResponse<List<Comment>> listCommentByTweetId(@PathVariable("id") Long tweetId) {
        return ServerResponse.createBySuccess(
                commentRepository.findByTweet_IdOrderByCreateTimeDesc(tweetId));
    }
}
