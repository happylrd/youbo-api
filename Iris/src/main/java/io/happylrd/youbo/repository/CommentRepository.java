package io.happylrd.youbo.repository;

import io.happylrd.youbo.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTweet_IdOrderByCreateTimeDesc(Long tweetId);
}
