package io.happylrd.repository;

import io.happylrd.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByTweet_Id(Integer tweetId);
}
