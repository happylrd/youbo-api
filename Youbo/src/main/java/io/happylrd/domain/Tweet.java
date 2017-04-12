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

    //TODO: will add the not null constraint later
    @ManyToOne
    private User user;

    private LocalDateTime addTime = LocalDateTime.now();

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }
}
