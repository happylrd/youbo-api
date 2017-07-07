package io.happylrd.youbo.repository;

import io.happylrd.youbo.model.domain.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

    List<Collection> findByUserId(Long userId);
}
