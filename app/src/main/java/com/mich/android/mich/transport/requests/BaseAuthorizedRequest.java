package com.mich.android.mich.transport.requests;

public class BaseAuthorizedRequest extends Request {
    String token;
    public BaseAuthorizedRequest(String token){
        this.token = token;
    }
}
