package io.happylrd.youbo.service;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.Collection;
import io.happylrd.youbo.model.domain.Comment;
import io.happylrd.youbo.model.domain.Favorite;
import io.happylrd.youbo.model.domain.Tweet;
import io.happylrd.youbo.model.dto.TweetFragmentDTO;
import io.happylrd.youbo.model.dto.UserDTO;
import io.happylrd.youbo.model.vo.LoginVO;
import io.happylrd.youbo.model.vo.RegisterVO;

import java.util.List;

public interface UserService {

    ServerResponse<UserDTO> register(RegisterVO registerVO);

    ServerResponse<UserDTO> login(LoginVO loginVO);

    ServerResponse<UserDTO> getInfo(Long userId);

    ServerResponse<Tweet> publishTweet(Long userId, List<TweetFragmentDTO> fragmentDTOs);

    ServerResponse<List<Tweet>> listMyTweet(Long userId);

    ServerResponse<Comment> publishComment(Long userId, Long tweetId, String content);

    ServerResponse<List<Comment>> listMyComment(Long userId);

    ServerResponse<Collection> collectTweet(Long userId, Long tweetId);

    ServerResponse<List<Collection>> listMyCollection(Long userId);

    ServerResponse<Collection> cancelCollection(Long collectionId);

    ServerResponse<Favorite> doFavorite(Long userId, Long tweetId);

    ServerResponse<List<Favorite>> listMyFavorite(Long userId);

    ServerResponse<Favorite> cancelFavorite(Long favoriteId);
}
