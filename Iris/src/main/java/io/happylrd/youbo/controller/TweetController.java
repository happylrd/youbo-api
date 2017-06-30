package io.happylrd.youbo.controller;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.domain.Tweet;
import io.happylrd.youbo.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

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
}
