package io.happylrd.youbo.common;

import io.happylrd.youbo.model.domain.*;
import io.happylrd.youbo.model.dto.OrgDTO;
import io.happylrd.youbo.model.dto.TweetDTO;
import io.happylrd.youbo.model.dto.TweetFragmentDTO;
import io.happylrd.youbo.model.dto.UserDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AssemblerUtil {

    public static UserDTO assembleIntoUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setNickname(user.getNickname());
        userDTO.setRealname(user.getRealname());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setGender(user.getGender());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setDescription(user.getDescription());
        return userDTO;
    }

    public static TweetDTO assembleIntoTweetDTO(Tweet tweet, String nickname, String avatar) {
        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setId(tweet.getId());
        tweetDTO.setCreateAt(tweet.getCreateAt());

        List<TweetFragmentDTO> tweetFragmentDTOs = tweet.getTweetFragments()
                .stream()
                .map(tweetFragment -> AssemblerUtil.assembleIntoTweetFragmentDTO(tweetFragment))
                .collect(Collectors.toList());
        tweetDTO.setFragmentDTOs(tweetFragmentDTOs);

        tweetDTO.setCommentSize(tweet.getComments().size());
        tweetDTO.setCollectionSize(tweet.getCollections().size());
        tweetDTO.setFavoriteSize(tweet.getFavorites().size());
        tweetDTO.setNickname(nickname);
        tweetDTO.setAvatar(avatar);

        return tweetDTO;
    }

    public static TweetFragmentDTO assembleIntoTweetFragmentDTO(TweetFragment tweetFragment) {
        TweetFragmentDTO tweetFragmentDTO = new TweetFragmentDTO();
        tweetFragmentDTO.setType(tweetFragment.getType());
        tweetFragmentDTO.setContent(tweetFragment.getContent());
        return tweetFragmentDTO;
    }



    public static Tweet assembleIntoTweet(List<TweetFragmentDTO> fragmentDTOs, Long userId) {
        Tweet tweet = new Tweet();
        Set<TweetFragment> fragments = new HashSet<>();

        for (TweetFragmentDTO fragmentDTO : fragmentDTOs) {
            TweetFragment fragment = new TweetFragment();
            fragment.setType(fragmentDTO.getType());
            fragment.setContent(fragmentDTO.getContent());

            // fragment will be saved when saving tweet

            fragments.add(fragment);
        }

        tweet.setTweetFragments(fragments);
        tweet.setUserId(userId);
        return tweet;
    }

    public static Org assembleIntoOrg(OrgDTO orgDTO, Long creatorId) {
        Org org = new Org();
        org.setName(orgDTO.getName());
        org.setDescription(orgDTO.getDescription());
        org.setPicture(orgDTO.getPicture());

        org.setOwnerId(creatorId);
        return org;
    }
}
