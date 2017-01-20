package com.mich.android.mich.transport.responses;

/**
 * Created by imac on 1/20/17.
 */

public class PostResponse {

    int user_id;
    String title;
    String image;
    String created_at;
    String updated_at;
    public int likes;

    public int getUser_id() {
        return user_id;
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

}
