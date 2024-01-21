package com.shing100.community.module.user;

import com.shing100.community.module.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneWithAuthoritiesByEmail(String email);
    boolean existsByUsername(String username);
    User findByEmail(String email);
}
