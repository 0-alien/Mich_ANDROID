package com.mich.android.mich.bean;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

public class Comment implements ParentListItem {

    private List<CommentReply> commentReplies;
    private String comment = "comment";

    public Comment(List<CommentReply> commentReplies){
        this.commentReplies = commentReplies;
    }


    @Override
    public List<CommentReply> getChildItemList() {
        return commentReplies;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
