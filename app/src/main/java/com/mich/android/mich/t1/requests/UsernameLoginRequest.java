package com.mich.android.mich.transport.requests;

public class UsernameLoginRequest extends LoginRequest{
    String username;
    String password;

    public UsernameLoginRequest(){
        loginType = LoginType.username;
    }

}
