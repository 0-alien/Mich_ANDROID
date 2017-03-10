package com.mich.android.mich.transport.requests;

/**
 * Created by sabagogolidze on 3/10/17.
 */

public class PostRequest extends BaseAuthorizedRequest {
    int postID;

    public PostRequest(int postID) {
        this.postID = postID;
    }
}
