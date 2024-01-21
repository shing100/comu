package com.shing100.community.feed.domain;

import com.shing100.community.user.domain.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
@AllArgsConstructor @NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Feed feed;

    @OrderBy("createdDate ASC")
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<Comment> childCommentList = new ArrayList<>();

    @Column
    private Long parentCommentId;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
