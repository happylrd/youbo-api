package io.happylrd.youbo.common;

import io.happylrd.youbo.model.domain.Tweet;
import io.happylrd.youbo.model.domain.TweetFragment;
import io.happylrd.youbo.model.domain.User;
import io.happylrd.youbo.model.dto.TweetFragmentDTO;
import io.happylrd.youbo.model.dto.UserDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}
