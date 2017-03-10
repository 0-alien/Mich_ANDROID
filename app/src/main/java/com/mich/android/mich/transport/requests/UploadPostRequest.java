package com.mich.android.mich.transport.requests;


public class UploadPostRequest extends BaseAuthorizedRequest {

    String title;
    String  image;

    public UploadPostRequest(String title, String image){
        this.title = title;
        this.image = image;
    }

}
