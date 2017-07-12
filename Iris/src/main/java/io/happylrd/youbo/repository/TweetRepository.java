package io.happylrd.youbo.repository;

import io.happylrd.youbo.model.domain.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findByUserIdOrderByCreateAtDesc(Long userId);

    List<Tweet> findByCollections_UserId(Long userId);

    List<Tweet> findByFavorites_UserId(Long userId);
}
