package io.happylrd.youbo.repository;

import io.happylrd.youbo.model.domain.OrgMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrgMemberRepository extends JpaRepository<OrgMember, Long> {

    List<OrgMember> findByGroupId(Long groupId);
}
