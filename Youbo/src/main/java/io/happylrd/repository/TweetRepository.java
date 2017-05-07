package io.happylrd.repository;

import io.happylrd.domain.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Integer> {

    List<Tweet> findByUser_Username(String username);

    List<Tweet> findByContentLikeIgnoreCase(String content);
}
