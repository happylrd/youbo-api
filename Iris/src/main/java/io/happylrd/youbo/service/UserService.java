package io.happylrd.youbo.service;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.*;
import io.happylrd.youbo.model.dto.OrgDTO;
import io.happylrd.youbo.model.dto.TweetDTO;
import io.happylrd.youbo.model.dto.TweetFragmentDTO;
import io.happylrd.youbo.model.dto.UserDTO;
import io.happylrd.youbo.model.vo.*;

import java.util.List;

public interface UserService {

    ServerResponse<UserDTO> register(RegisterVO registerVO);

    ServerResponse<UserDTO> login(LoginVO loginVO);

    ServerResponse<UserDTO> getInfo(String username);

    ServerResponse<UserVO> getInfoById(Long id);

    ServerResponse<UserInfoVO> getNormalInfo(Long id);

    ServerResponse<UserInfoVO> updateInfo(Long userId, UserInfoVO userInfoVO);

    ServerResponse updateAvatar(Long userId, String avatarUrl);

    ServerResponse<Tweet> publishTweet(Long userId, List<TweetFragmentDTO> fragmentDTOs);

    ServerResponse<List<TweetDTO>> listMyTweet(Long userId);

    ServerResponse<Comment> publishComment(Long userId, Long tweetId, String content);

    ServerResponse<List<CommentVO>> listMyComment(Long userId);

    ServerResponse<Collection> collectTweet(Long userId, Long tweetId);

    ServerResponse<List<TweetDTO>> listMyCollection(Long userId);

    ServerResponse<Collection> cancelCollection(Long collectionId);

    ServerResponse<Favorite> doFavorite(Long userId, Long tweetId);

    ServerResponse<List<TweetDTO>> listMyFavorite(Long userId);

    ServerResponse<Favorite> cancelFavorite(Long favoriteId);

    ServerResponse<UserFollow> doFollowing(Long id, Long targetId);

    ServerResponse<List<FollowVO>> listMyFollowing(Long userId);

    ServerResponse<List<FollowVO>> listMyFollower(Long userId);

    ServerResponse<Org> createOrg(Long userId, OrgDTO orgDTO);

    ServerResponse<List<Org>> listMyOrg(Long userId);

    ServerResponse<OrgMember> addMemberToOrg(Long ownerId, Long orgId, Long userId);
}
