package com.shing100.community.user;

import com.shing100.community.user.domain.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorityInitService {

    private final AuthorityRepository authorityRepository;

    @PostConstruct
    public void initAuthorities() {
        createAuthorityIfNotExists("ROLE_USER");
        createAuthorityIfNotExists("ROLE_ADMIN");
    }

    private void createAuthorityIfNotExists(String authorityName) {
        if (!authorityRepository.existsByAuthorityName(authorityName)) {
            Authority authority = new Authority();
            authority.setAuthorityName(authorityName);
            authorityRepository.save(authority);
        }
    }
}
