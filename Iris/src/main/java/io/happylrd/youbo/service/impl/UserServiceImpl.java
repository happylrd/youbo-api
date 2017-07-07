package io.happylrd.youbo.service.impl;

import io.happylrd.youbo.common.AssemblerUtil;
import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.*;
import io.happylrd.youbo.model.dto.TweetFragmentDTO;
import io.happylrd.youbo.model.dto.UserDTO;
import io.happylrd.youbo.model.vo.LoginVO;
import io.happylrd.youbo.model.vo.RegisterVO;
import io.happylrd.youbo.repository.*;
import io.happylrd.youbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public ServerResponse<UserDTO> register(RegisterVO registerVO) {
        long resultCount = userRepository.countByUsername(registerVO.getUsername());
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }

        User user = new User();
        user.setUsername(registerVO.getUsername());
        user.setPassword(registerVO.getPassword());
        user.setNickname(registerVO.getNickname());

        Role role = roleRepository.findByName("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(role)));

        userRepository.save(user);

        // do log

        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<UserDTO> login(LoginVO loginVO) {
        long resultCount = userRepository.countByUsername(loginVO.getUsername());
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        // MD5 process for password

        User user = userRepository.findByUsernameAndPassword(
                loginVO.getUsername(), loginVO.getPassword());

        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }

        UserDTO userDTO = AssemblerUtil.assembleIntoUserDTO(user);

        return ServerResponse.createBySuccess("登录成功", userDTO);
    }

    @Override
    public ServerResponse<UserDTO> getInfo(Long userId) {
        User user = userRepository.findOne(userId);

        if (user == null) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        UserDTO userDTO = AssemblerUtil.assembleIntoUserDTO(user);

        return ServerResponse.createBySuccess(userDTO);
    }

    @Override
    public ServerResponse<Tweet> publishTweet(Long userId, List<TweetFragmentDTO> fragmentDTOs) {
        Tweet tweet = AssemblerUtil.assembleIntoTweet(fragmentDTOs, userId);
        tweetRepository.save(tweet);
        return ServerResponse.createBySuccessMessage("发表Tweet成功");
    }

    @Override
    public ServerResponse<List<Tweet>> listMyTweet(Long userId) {
        return ServerResponse.createBySuccess(
                tweetRepository.findByUserIdOrderByCreateAtDesc(userId));
    }

    @Override
    public ServerResponse<Comment> publishComment(Long userId, Long tweetId, String content) {
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setTweetId(tweetId);
        comment.setContent(content);
        commentRepository.save(comment);
        return ServerResponse.createBySuccessMessage("发表评论成功");
    }

    @Override
    public ServerResponse<List<Comment>> listMyComment(Long userId) {
        return ServerResponse.createBySuccess(
                commentRepository.findByUserId(userId));
    }

    @Override
    public ServerResponse<Collection> collectTweet(Long userId, Long tweetId) {
        long resultCount = collectionRepository
                .countByUserIdAndTweetIdAndEnabledTrue(userId, tweetId);
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("收藏已存在");
        }

        Collection collection = new Collection();
        collection.setUserId(userId);
        collection.setTweetId(tweetId);
        return ServerResponse.createBySuccess("添加收藏成功",
                collectionRepository.save(collection));
    }

    @Override
    public ServerResponse<List<Collection>> listMyCollection(Long userId) {
        return ServerResponse.createBySuccess(
                collectionRepository.findByUserIdAndEnabledTrue(userId));
    }

    @Override
    public ServerResponse<Collection> cancelCollection(Long collectionId) {
        Collection collection = collectionRepository.findByIdAndEnabledTrue(collectionId);
        if (collection == null) {
            return ServerResponse.createByErrorMessage("收藏不存在");
        }

        collection.setEnabled(false);
        return ServerResponse.createBySuccess("取消收藏成功",
                collectionRepository.save(collection));
    }

    @Override
    public ServerResponse<Favorite> doFavorite(Long userId, Long tweetId) {
        long resultCount = favoriteRepository
                .countByUserIdAndTweetIdAndEnabledTrue(userId, tweetId);
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("喜欢已存在");
        }

        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setTweetId(tweetId);
        return ServerResponse.createBySuccess("添加喜欢成功",
                favoriteRepository.save(favorite));
    }

    @Override
    public ServerResponse<List<Favorite>> listMyFavorite(Long userId) {
        return ServerResponse.createBySuccess(
                favoriteRepository.findByUserIdAndEnabledTrue(userId));
    }

    @Override
    public ServerResponse<Favorite> cancelFavorite(Long favoriteId) {
        Favorite favorite = favoriteRepository.findByIdAndEnabledTrue(favoriteId);
        if (favorite == null) {
            return ServerResponse.createByErrorMessage("喜欢不存在");
        }

        favorite.setEnabled(false);
        return ServerResponse.createBySuccess("取消喜欢成功",
                favoriteRepository.save(favorite));
    }
}
