package io.happylrd.youbo.service;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.Comment;
import io.happylrd.youbo.model.domain.Tweet;
import io.happylrd.youbo.model.dto.TweetDTO;
import io.happylrd.youbo.model.vo.TweetDetailVO;

import java.util.List;

public interface TweetService {

    ServerResponse<List<TweetDTO>> listTweet();

    ServerResponse<TweetDetailVO> getTweet(Long id);

    ServerResponse<List<Comment>> listComment(Long tweetId);
}
