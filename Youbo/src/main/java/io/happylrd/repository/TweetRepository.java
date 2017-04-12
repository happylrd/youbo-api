package io.happylrd.repository;

import io.happylrd.domain.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Integer> {

}
