package io.happylrd.youbo.service.impl;

import io.happylrd.youbo.common.AssemblerUtil;
import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.Comment;
import io.happylrd.youbo.model.domain.Tweet;
import io.happylrd.youbo.model.domain.User;
import io.happylrd.youbo.model.dto.TweetDTO;
import io.happylrd.youbo.model.dto.TweetFragmentDTO;
import io.happylrd.youbo.model.vo.CommentVO;
import io.happylrd.youbo.model.vo.TweetDetailVO;
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
    public ServerResponse<List<TweetDTO>> listTopTweet(Integer number) {
        Sort sort = new Sort(Sort.Direction.DESC, "createAt");

        List<Tweet> tweetList = tweetRepository.findAll(sort);
        if (tweetList.size() >= number) {
            tweetList = tweetList.subList(0, number);
        }

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
    public ServerResponse<TweetDetailVO> getTweet(Long id) {
        Tweet tweet = tweetRepository.findOne(id);
        TweetDetailVO tweetDetailVO = assembleIntoTweetDetailVO(tweet);
        return ServerResponse.createBySuccess(tweetDetailVO);
    }

    @Override
    public ServerResponse<List<Comment>> listComment(Long tweetId) {
        return ServerResponse.createBySuccess(
                commentRepository.findByTweetId(tweetId));
    }

    private TweetDetailVO assembleIntoTweetDetailVO(Tweet tweet) {
        TweetDetailVO tweetDetailVO = new TweetDetailVO();
        tweetDetailVO.setId(tweet.getId());
        tweetDetailVO.setCreateAt(tweet.getCreateAt());

        List<TweetFragmentDTO> tweetFragmentDTOs = tweet.getTweetFragments()
                .stream()
                .map(tweetFragment -> AssemblerUtil.assembleIntoTweetFragmentDTO(tweetFragment))
                .collect(Collectors.toList());
        tweetDetailVO.setFragmentDTOs(tweetFragmentDTOs);

        tweetDetailVO.setCommentSize(tweet.getComments().size());
        tweetDetailVO.setCollectionSize(tweet.getCollections().size());
        tweetDetailVO.setFavoriteSize(tweet.getFavorites().size());

        User user = userRepository.findOne(tweet.getUserId());
        tweetDetailVO.setNickname(user.getNickname());
        tweetDetailVO.setAvatar(user.getAvatar());

        List<CommentVO> commentVOs = tweet.getComments()
                .stream()
                .map(comment -> assembleIntoCommentVO(comment))
                .collect(Collectors.toList());
        tweetDetailVO.setCommentVOs(commentVOs);

        return tweetDetailVO;
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
}
