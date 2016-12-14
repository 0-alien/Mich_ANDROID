package com.mich.android.mich.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mich.android.mich.BaseActivity;
import com.mich.android.mich.R;
import com.mich.android.mich.bean.Comment;
import com.mich.android.mich.bean.CommentReply;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private CommentsRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = (RecyclerView)findViewById(R.id.list);
        List<Comment> comments = new ArrayList<>();
        List<CommentReply> commentReplies = new ArrayList<>();
        commentReplies.add(new CommentReply());
        commentReplies.add(new CommentReply());
        commentReplies.add(new CommentReply());
        for (int i=0; i < 30; i++) {
            comments.add(new Comment(commentReplies));
        }
        adapter = new CommentsRecyclerAdapter(this,comments);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_comments;
    }
}
