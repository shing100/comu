package com.shing100.community.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class User {

    @Id @Unique
    private String email;

    @Column(name = "username", length = 50)
    private String username;

    @Column(unique = true, name = "nickname", length = 50)
    private String nickname;

    @JsonIgnore
    @Column(name = "password", length = 100)
    private String password;

    @JsonIgnore
    @Column(name = "active")
    private boolean active;

    private boolean emailVerified;

    private String emailCheckToken;
    private LocalDateTime emailCheckTokenGeneratedAt;
    private LocalDateTime joinedAt;

    @Column(name = "bio", length = 200)
    private String bio;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String avatar;

    private String location;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "email", referencedColumnName = "email")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
        this.emailCheckTokenGeneratedAt = LocalDateTime.now();
    }

    public void completeSignUp() {
        this.emailVerified = true;
        this.joinedAt = LocalDateTime.now();
    }

    public boolean isValidToken(String token) {
        return this.emailCheckToken.equals(token);
    }

    public boolean canSendConfirmEmail() {
        return this.emailCheckTokenGeneratedAt.isBefore(LocalDateTime.now().minusHours(1));
    }
}
