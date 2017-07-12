package io.happylrd.youbo.repository;

import io.happylrd.youbo.model.domain.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {

    Long countByOriginIdAndTargetId(Long originId, Long targetId);
}
