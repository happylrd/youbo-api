package io.happylrd.youbo.repository;

import io.happylrd.youbo.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Long countByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
