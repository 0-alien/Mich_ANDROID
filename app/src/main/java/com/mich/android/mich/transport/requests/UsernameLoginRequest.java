package com.mich.android.mich.transport.requests;

public class UsernameLoginRequest extends Request{
    String username;
    String password;
    int type = 0;



    public UsernameLoginRequest(String username, String password){
        this.username = username;
        this.password = password;
        type = 0;
    }

}
