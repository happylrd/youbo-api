package io.happylrd.youbo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class TweetDTO {

    private Long id;

    private LocalDateTime createAt;

    @JsonProperty(value = "fragments")
    private List<TweetFragmentDTO> fragmentDTOs;

    private Integer commentSize;

    private Integer collectionSize;

    private Integer favoriteSize;

    private Long userId;

    private String nickname;

    private String avatar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public List<TweetFragmentDTO> getFragmentDTOs() {
        return fragmentDTOs;
    }

    public void setFragmentDTOs(List<TweetFragmentDTO> fragmentDTOs) {
        this.fragmentDTOs = fragmentDTOs;
    }

    public Integer getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(Integer commentSize) {
        this.commentSize = commentSize;
    }

    public Integer getCollectionSize() {
        return collectionSize;
    }

    public void setCollectionSize(Integer collectionSize) {
        this.collectionSize = collectionSize;
    }

    public Integer getFavoriteSize() {
        return favoriteSize;
    }

    public void setFavoriteSize(Integer favoriteSize) {
        this.favoriteSize = favoriteSize;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
