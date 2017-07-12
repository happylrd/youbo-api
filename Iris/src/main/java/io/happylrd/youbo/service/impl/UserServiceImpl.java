package io.happylrd.youbo.service.impl;

import com.google.common.collect.Maps;
import io.happylrd.youbo.common.AssemblerUtil;
import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.*;
import io.happylrd.youbo.model.dto.OrgDTO;
import io.happylrd.youbo.model.dto.TweetFragmentDTO;
import io.happylrd.youbo.model.dto.UserDTO;
import io.happylrd.youbo.model.vo.*;
import io.happylrd.youbo.repository.*;
import io.happylrd.youbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private OrgRepository orgRepository;

    @Autowired
    private OrgMemberRepository orgMemberRepository;

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
    public ServerResponse<UserDTO> getInfo(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        UserDTO userDTO = AssemblerUtil.assembleIntoUserDTO(user);

        return ServerResponse.createBySuccess(userDTO);
    }

    @Override
    public ServerResponse<UserVO> getInfoById(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        UserVO userVO = assembleIntoUserVO(user);
        return ServerResponse.createBySuccess(userVO);
    }

    /**
     * can be extract into util
     */
    private UserVO assembleIntoUserVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setNickname(user.getNickname());
        userVO.setAvatar(user.getAvatar());
        userVO.setDescription(user.getDescription());
        userVO.setTweetSize(user.getTweets().size());
        userVO.setFollowingSize(user.getFollowing().size());
        userVO.setFollowerSize(user.getFollowers().size());
        userVO.setCommentSize(user.getComments().size());
        userVO.setCollectionSize(user.getCollections().size());
        userVO.setFavoriteSize(user.getFavorites().size());
        return userVO;
    }

    @Override
    public ServerResponse<UserInfoVO> updateInfo(Long userId, UserInfoVO userInfoVO) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        user.setNickname(userInfoVO.getNickname());
        user.setRealname(userInfoVO.getRealname());
//        user.setAvatar(userInfoVO.getAvatar());
        user.setGender(userInfoVO.getGender());
        user.setBirthday(userInfoVO.getBirthday());
        user.setDescription(userInfoVO.getDescription());

        userRepository.save(user);

        return ServerResponse.createBySuccessMessage("更新用户信息成功");
    }

    @Override
    public ServerResponse updateAvatar(Long userId, String avatarUrl) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        user.setAvatar(avatarUrl);
        userRepository.save(user);

        Map<String, String> avatarMap = Maps.newHashMap();
        avatarMap.put("avatar", avatarUrl);
        return ServerResponse.createBySuccess("更新用户头像成功", avatarMap);
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
    public ServerResponse<List<CommentVO>> listMyComment(Long userId) {
        List<Comment> comments = commentRepository.findByUserId(userId);

        List<CommentVO> commentVOs = comments.stream()
                .map(comment -> assembleIntoCommentVO(comment))
                .collect(Collectors.toList());

        return ServerResponse.createBySuccess(commentVOs);
    }

    private CommentVO assembleIntoCommentVO(Comment comment) {
        CommentVO commentVO = new CommentVO();
        commentVO.setId(comment.getId());
        commentVO.setContent(comment.getContent());
        commentVO.setCreateAt(comment.getCreateAt());

        User user = userRepository.findOne(comment.getUserId());
        commentVO.setNickname(user.getNickname());
        commentVO.setAvatar(user.getAvatar());

        return commentVO;
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

    /**
     * orgDTO need not null check, will be added later
     */
    @Override
    public ServerResponse<Org> createOrg(Long userId, OrgDTO orgDTO) {
        long resultCount = orgRepository.countByName(orgDTO.getName());
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("组织名已存在");
        }
        Org org = AssemblerUtil.assembleIntoOrg(orgDTO, userId);
        org = orgRepository.save(org);
        return ServerResponse.createBySuccess("创建组织成功", org);
    }

    @Override
    public ServerResponse<List<Org>> listMyOrg(Long userId) {
        return ServerResponse.createBySuccess(
                orgRepository.findByOwnerId(userId));
    }

    @Override
    public ServerResponse<OrgMember> addMemberToOrg(Long ownerId, Long orgId, Long userId) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            return ServerResponse.createByErrorMessage("待添加到组织的用户不存在");
        }
        Org org = orgRepository.findOne(orgId);
        if (org == null) {
            return ServerResponse.createByErrorMessage("组织不存在");
        }

        // need to judge org' owner

        OrgMember orgMember = new OrgMember();
        orgMember.setUser(user);
        orgMember.setGroupId(orgId);
        orgMember = orgMemberRepository.save(orgMember);
        return ServerResponse.createBySuccess("将另一个用户添加到组织成功", orgMember);
    }
}
