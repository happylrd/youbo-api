package io.happylrd.youbo.repository;

import io.happylrd.youbo.model.domain.Org;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrgRepository extends JpaRepository<Org, Long> {

    List<Org> findByOwnerId(Long userId);

    Long countByName(String name);
}
