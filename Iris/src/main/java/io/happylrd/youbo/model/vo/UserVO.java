package io.happylrd.youbo.model.vo;

public class UserVO {

    private String nickname;

    private String avatar;

    private String description;

    private int tweetSize;

    private int followingSize;

    private int followerSize;

    private int commentSize;

    private int collectionSize;

    private int favoriteSize;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTweetSize() {
        return tweetSize;
    }

    public void setTweetSize(int tweetSize) {
        this.tweetSize = tweetSize;
    }

    public int getFollowingSize() {
        return followingSize;
    }

    public void setFollowingSize(int followingSize) {
        this.followingSize = followingSize;
    }

    public int getFollowerSize() {
        return followerSize;
    }

    public void setFollowerSize(int followerSize) {
        this.followerSize = followerSize;
    }

    public int getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(int commentSize) {
        this.commentSize = commentSize;
    }

    public int getCollectionSize() {
        return collectionSize;
    }

    public void setCollectionSize(int collectionSize) {
        this.collectionSize = collectionSize;
    }

    public int getFavoriteSize() {
        return favoriteSize;
    }

    public void setFavoriteSize(int favoriteSize) {
        this.favoriteSize = favoriteSize;
    }
}
