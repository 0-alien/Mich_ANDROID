package com.mich.android.mich.transport.requests;

/**
 * Created by sabagogolidze on 3/10/17.
 */

public class UserByIdRequest extends BaseAuthorizedRequest {
    int userID;
    public UserByIdRequest(int userID){
        this.userID = userID;
    }
}
