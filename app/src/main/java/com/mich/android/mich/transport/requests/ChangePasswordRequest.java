package com.mich.android.mich.transport.requests;

/**
 * Created by sabagogolidze on 3/10/17.
 */

public class ChangePasswordRequest extends BaseAuthorizedRequest {
    String password;
    public ChangePasswordRequest(String password){
        this.password = password;
    }
}
