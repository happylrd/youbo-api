package io.happylrd.youbo.service.impl;

import io.happylrd.youbo.common.AssemblerUtil;
import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.Comment;
import io.happylrd.youbo.model.domain.Tweet;
import io.happylrd.youbo.model.domain.User;
import io.happylrd.youbo.model.dto.TweetDTO;
import io.happylrd.youbo.repository.CommentRepository;
import io.happylrd.youbo.repository.TweetRepository;
import io.happylrd.youbo.repository.UserRepository;
import io.happylrd.youbo.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ServerResponse<List<TweetDTO>> listTweet() {
        Sort sort = new Sort(Sort.Direction.DESC, "createAt");

        List<Tweet> tweetList = tweetRepository.findAll(sort);

        List<TweetDTO> tweetDTOList = tweetList.stream()
                .map(tweet -> {
                    User user = userRepository.findOne(tweet.getUserId());
                    String nickname = user.getNickname();
                    String avatar = user.getAvatar();
                    return AssemblerUtil.assembleIntoTweetDTO(tweet, nickname, avatar);
                })
                .collect(Collectors.toList());

        return ServerResponse.createBySuccess(tweetDTOList);
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
