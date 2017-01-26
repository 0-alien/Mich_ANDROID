package com.mich.android.mich.transport.requests;

public class RegisterRequest extends Request {

    String firstname = "saba";
    String lastname = "gogolidze";
    String email;
    String username;
    String password;

    public RegisterRequest(String username,String email, String password){
        this.email = email;
        this.username = username;
        this.password = password;
    }

}
