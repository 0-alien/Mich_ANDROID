package com.mich.android.mich.transport.requests;

public class RegisterRequest extends Request {

    String name;
    String email;
    String username;
    String password;

    public RegisterRequest(String username,String email, String password, String name){
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
    }

}
