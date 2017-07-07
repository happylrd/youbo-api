package io.happylrd.youbo.service.impl;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.Comment;
import io.happylrd.youbo.model.domain.Tweet;
import io.happylrd.youbo.repository.CommentRepository;
import io.happylrd.youbo.repository.TweetRepository;
import io.happylrd.youbo.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public ServerResponse<List<Tweet>> listTweet() {
        Sort sort = new Sort(Sort.Direction.DESC, "createAt");
        return ServerResponse.createBySuccess(
                tweetRepository.findAll(sort));
    }

    @Override
    public ServerResponse<Tweet> getTweet(Long id) {
        return ServerResponse.createBySuccess(
                tweetRepository.findOne(id));
    }

    @Override
    public ServerResponse<List<Comment>> listComment(Long tweetId) {
        return ServerResponse.createBySuccess(
                commentRepository.findByTweetId(tweetId));
    }
}
