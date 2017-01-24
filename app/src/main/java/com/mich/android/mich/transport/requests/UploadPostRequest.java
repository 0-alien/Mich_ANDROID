package com.mich.android.mich.transport.requests;

/**
 * Created by imac on 1/20/17.
 */

public class UploadPostRequest extends BaseAuthorizedRequest {

    String title;
    String  image;

    public UploadPostRequest(String title, String image){
        super();
        this.title = title;
        this.image = image;
    }

}
