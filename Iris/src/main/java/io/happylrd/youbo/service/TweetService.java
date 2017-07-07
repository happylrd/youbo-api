package io.happylrd.youbo.service;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.Tweet;

import java.util.List;

public interface TweetService {

    ServerResponse<List<Tweet>> listTweet();

    ServerResponse<Tweet> getTweet(Long id);
}
