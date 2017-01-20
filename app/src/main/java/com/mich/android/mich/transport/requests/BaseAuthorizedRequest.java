package com.mich.android.mich.transport.requests;

import com.mich.android.mich.App;

public class BaseAuthorizedRequest extends Request {
    String token;
    public BaseAuthorizedRequest(){
        this.token = App.getInstance().getLoginToken();
    }

    public BaseAuthorizedRequest(String token){
        this.token = token;
    }
}
