package com.mich.android.mich.transport.requests;

/**
 * Created by sabagogolidze on 3/10/17.
 */

public class AddCommentRequest extends BaseAuthorizedRequest{
    int postID;
    String comment;

    public AddCommentRequest(int postID, String comment){
        this.postID = postID;
        this.comment = comment;
    }
}
