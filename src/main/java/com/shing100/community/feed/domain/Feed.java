package com.shing100.community.feed.domain;

import com.shing100.community.user.domain.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class Feed {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", length = 100)
    private String title;
    @Column(name = "content", length = 1000)
    private String content;
    @Lob @Basic(fetch = FetchType.EAGER)
    private String image;
    private String video;
    private String audio;
    private String link;
    private FeedType feedType;
    private FeedStatus feedStatus;
    private FeedCategory feedCategory;
    @ElementCollection
    private Set<String> feedTag = new HashSet<>();

    private boolean published;
    private boolean useBanner;

    @ManyToOne
    private User user;
    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;
    private LocalDateTime deletedDate;
    private String deletedYn;
    private String deletedReason;

    @ManyToOne
    private User deletedAuthor;

}
