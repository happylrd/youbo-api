package io.happylrd.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Tweet {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String content;

    private String imageUrls;

    //TODO: will add the not null constraint later
    @ManyToOne
    private User user;

    private LocalDateTime createTime = LocalDateTime.now();
    private LocalDateTime updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}