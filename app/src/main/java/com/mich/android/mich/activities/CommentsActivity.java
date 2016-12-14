package com.mich.android.mich.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mich.android.mich.BaseActivity;
import com.mich.android.mich.R;
import com.mich.android.mich.bean.Comment;
import com.mich.android.mich.bean.CommentReply;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private CommentsRecyclerAdapter adapter;
    Toolbar toolbar;

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
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_comments;
    }
}
