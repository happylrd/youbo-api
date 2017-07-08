package io.happylrd.youbo.repository;

import io.happylrd.youbo.model.domain.Org;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgRepository extends JpaRepository<Org, Long> {

    Long countByName(String name);
}
