package io.happylrd.youbo.repository;

import io.happylrd.youbo.model.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Long countByUserIdAndTweetIdAndEnabledTrue(Long userId, Long tweetId);

    List<Favorite> findByUserIdAndEnabledTrue(Long userId);

    Favorite findByIdAndEnabledTrue(Long id);
}
