package com.mich.android.mich.transport.requests;

import com.mich.android.mich.transport.Request;

public class UsernameLoginRequest extends Request{
    String email;
    String password;
    int type = 1;



    public UsernameLoginRequest(String username, String password){
        this.email = username;
        this.password = password;
    }

}
