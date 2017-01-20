package com.mich.android.mich.transport.requests;

/**
 * Created by imac on 1/20/17.
 */

public class UploadPostRequest extends BaseAuthorizedRequest {

    String title;

    public UploadPostRequest(String title){
        super();
        this.title = title;
    }

}
