package io.happylrd.youbo.repository;

import io.happylrd.youbo.domain.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
