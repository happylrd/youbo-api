package io.happylrd.youbo.repository;

import io.happylrd.youbo.model.domain.TweetFragment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetFragmentRepository extends JpaRepository<TweetFragment, Long> {
}
