package io.happylrd.youbo.model.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Tweet")
public class Tweet {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column(nullable = false)
    private String txtContent;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "creatorId")
    private User creator;

    /**
     * will be improved later
     */
    @OneToMany
    @JoinColumn(name = "fragmentId")
    private Fragment fragment;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tweetId")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tweetId")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Favorite> favorites = new HashSet<>();
}
