package io.happylrd.youbo.repository;

import io.happylrd.youbo.model.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByUserId(Long userId);

    List<Comment> findByTweetId(Long tweetId);
}
