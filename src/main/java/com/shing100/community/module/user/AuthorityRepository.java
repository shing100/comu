package com.shing100.community.module.user;

import com.shing100.community.module.user.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    boolean existsByAuthorityName(String authorityName);
}
