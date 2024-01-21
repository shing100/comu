package com.shing100.community.module.feed.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shing100.community.module.user.domain.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
@JsonIgnoreProperties
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
    @Enumerated(EnumType.STRING)
    private FeedType feedType;
    @Enumerated(EnumType.STRING)
    private FeedStatus feedStatus;
    @Enumerated(EnumType.STRING)
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
