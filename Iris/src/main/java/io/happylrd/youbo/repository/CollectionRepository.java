package io.happylrd.youbo.repository;

import io.happylrd.youbo.model.domain.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

    Long countByUserIdAndTweetIdAndEnabledTrue(Long userId, Long tweetId);

    List<Collection> findByUserIdAndEnabledTrue(Long userId);

    Collection findByIdAndEnabledTrue(Long id);
}
