package io.happylrd.youbo.model.domain;

import io.happylrd.youbo.common.ModelConst;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column(unique = true, nullable = false, length = 128)
    private String username;

    @Column(nullable = false)
    private String password;

    /**
     * unique should be maintained in logic
     */
    @Column(length = 64)
    private String email;

    /**
     * unique should be maintained in logic
     */
    @Column(length = 64)
    private String phone;

    /**
     * unique should be maintained in logic
     */
    @Column
    private String nickname;

    @Column
    private String realname;

    @Column
    private String avatar;

    @Column(nullable = false)
    private int gender = ModelConst.Gender.UNKNOWN;

    @Column
    private LocalDate birthday;

    @Column
    private String description;

    @Column(nullable = false)
    private int role = ModelConst.Role.NORMAL;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    /**
     * lazy load
     * don't query the set when loading user
     * just query number and don't load the set when invoke .size() method
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "originId")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<UserFollow> following = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "targetId")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<UserFollow> followers = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ownerId")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Group> groups = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Collection> collections = new HashSet<>();
}
