package io.happylrd.youbo.service;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.*;
import io.happylrd.youbo.model.dto.OrgDTO;
import io.happylrd.youbo.model.dto.TweetFragmentDTO;
import io.happylrd.youbo.model.dto.UserDTO;
import io.happylrd.youbo.model.vo.LoginVO;
import io.happylrd.youbo.model.vo.RegisterVO;
import io.happylrd.youbo.model.vo.UserInfoVO;
import io.happylrd.youbo.model.vo.UserVO;

import java.util.List;

public interface UserService {

    ServerResponse<UserDTO> register(RegisterVO registerVO);

    ServerResponse<UserDTO> login(LoginVO loginVO);

    ServerResponse<UserDTO> getInfo(String username);

    ServerResponse<UserVO> getInfoById(Long id);

    ServerResponse<UserInfoVO> updateInfo(Long userId, UserInfoVO userInfoVO);

    ServerResponse updateAvatar(Long userId, String avatarUrl);

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

    ServerResponse<Org> createOrg(Long userId, OrgDTO orgDTO);

    ServerResponse<List<Org>> listMyOrg(Long userId);

    ServerResponse<OrgMember> addMemberToOrg(Long ownerId, Long orgId, Long userId);
}
