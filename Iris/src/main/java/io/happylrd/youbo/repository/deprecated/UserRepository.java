package io.happylrd.youbo.repository.deprecated;

import io.happylrd.youbo.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
