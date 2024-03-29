package com.mich.android.mich.transport.responses;

/**
 * Created by imac on 1/20/17.
 */

public class PostResponse {

    int id;
    int userid;
    String title;
    String image;
    String created_at;
    String updated_at;
    String avatar;
    int likes;
    int mylikes;
    int ncomments;

    public int getUserid() {
        return userid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int getLikes() {
        return likes;
    }

    public int getId() {
        return id;
    }

    public int getMylikes() {
        return mylikes;
    }

    public int getNcomments() {
        return ncomments;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
