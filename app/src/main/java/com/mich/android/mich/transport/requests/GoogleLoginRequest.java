package com.mich.android.mich.transport.requests;

public class GoogleLoginRequest extends SocialLoginRequest {

    public GoogleLoginRequest(){
        loginType = LoginType.google;
    }

}
