package com.mich.android.mich.transport.responses;

/**
 * Created by sabagogolidze on 1/18/17.
 */

public class UserResponse {

   int id;
    String name;
    String username;
    String email;
    String avatar;
    int nfollowers;
    int nfollowing;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getNfollowers() {
        return nfollowers;
    }

    public int getNfollowing() {
        return nfollowing;
    }
}
