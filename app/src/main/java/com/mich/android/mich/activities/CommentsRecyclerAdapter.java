package com.mich.android.mich.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.mich.android.mich.R;
import com.mich.android.mich.bean.Comment;

import java.util.List;

public class CommentsRecyclerAdapter extends ExpandableRecyclerAdapter<CommentsRecyclerAdapter.CommentViewHolder, CommentsRecyclerAdapter.CommentReplyViewHolder> {


    private List<Comment> comments;
    private LayoutInflater mInflator;

    public CommentsRecyclerAdapter(Context context, @NonNull List<Comment> parentItemList) {
        super(parentItemList);
        this.comments = parentItemList;
        mInflator = LayoutInflater.from(context);
    }

    @Override
    public CommentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View recipeView = mInflator.inflate(R.layout.activity_comments_recycler_item, parentViewGroup, false);
        return new CommentViewHolder(recipeView);
    }

    @Override
    public CommentReplyViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View recipeView = mInflator.inflate(R.layout.activity_comment_replies_recycler_item, childViewGroup, false);
        return new CommentReplyViewHolder(recipeView);
    }

    @Override
    public void onBindParentViewHolder(CommentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {

    }

    @Override
    public void onBindChildViewHolder(CommentReplyViewHolder childViewHolder, int position, Object childListItem) {

    }

    public class CommentReplyViewHolder extends ChildViewHolder {
        public CommentReplyViewHolder(View itemView) {
            super(itemView);

        }
    }

    public class CommentViewHolder extends ParentViewHolder {
        public CommentViewHolder(View itemView) {
            super(itemView);

        }
    }
}
