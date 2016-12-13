package com.mich.android.mich.transport.requests;

public class FacebookLoginRequest extends SocialLoginRequest {
    public FacebookLoginRequest(String token){
        loginType = LoginType.facebook;
        this.token = token;
    }



}
