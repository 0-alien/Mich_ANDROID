package com.mich.android.mich.activities;

import android.app.ProgressDialog;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mich.android.mich.App;
import com.mich.android.mich.BaseActivity;
import com.mich.android.mich.R;
import com.mich.android.mich.Utils;
import com.mich.android.mich.fragments.PostImagesRecyclerViewAdapter;
import com.mich.android.mich.transport.DoPostCallback;
import com.mich.android.mich.transport.MichTransport;
import com.mich.android.mich.transport.responses.BoolResponse;
import com.mich.android.mich.transport.responses.PostResponse;
import com.mich.android.mich.transport.responses.UserResponse;

import java.util.ArrayList;

public class UserDetailsActivity extends BaseActivity {

    RecyclerView recyclerView;
    ImageView userAvatar;
    private UserResponse user;
    Toolbar toolbar;
    private TextView followEditTV;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        userAvatar = (ImageView)findViewById(R.id.img_avatar);
        followEditTV = (TextView)findViewById(R.id.follow_edit_tv);
        toolbar.findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Bundle b = getIntent().getExtras();
        if(b.getBoolean("withID")){
            dialog = ProgressDialog.show(this, "",
                    "Loading. Please wait...", true);
            MichTransport.getInstance().loadUserById(this, b.getInt("userID"), new DoPostCallback<UserResponse>() {
                @Override
                public void onLoad(int code, String message, UserResponse data) {
                    dialog.dismiss();
                    if(code == MichTransport.LOAD_SUCCESS){
                        user = data;
                        populateUser();
                    }
                }
            });

        }else {
            user = new Gson().fromJson(b.getString("userJSON"), UserResponse.class);
            populateUser();
        }


    }

    private void populateUser() {
        initFollowEditTV();
        Utils.loadUrlInImageView(this,userAvatar,user.getAvatar());
        loadUserPostImages();
    }

    private void initFollowEditTV() {
        if(App.getInstance().getUserID() == user.getId()){
            followEditTV.setText("EDIT PROFILE");
            followEditTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // edit profile

                }
            });
        }else {

            MichTransport.getInstance().isFollowing(this, user.getId(), new DoPostCallback<BoolResponse>() {
                @Override
                public void onLoad(int code, String message, BoolResponse data) {
                    if(code == MichTransport.LOAD_SUCCESS){

                        if (data.getResult()){
                            followEditTV.setText("UNFOLLOW");
                            followEditTV.setOnClickListener(onUnFollowCLickListener);
                        } else {
                            followEditTV.setText("FOLLOW");
                            followEditTV.setOnClickListener(onFollowCLickListener);
                        }

                    }
                }
            });

        }
    }

    private View.OnClickListener onFollowCLickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MichTransport.getInstance().followUserByID(UserDetailsActivity.this, user.getId(), new DoPostCallback<Void>() {
                @Override
                public void onLoad(int code, String message, Void data) {
                    if(code == MichTransport.LOAD_SUCCESS){
                        followEditTV.setText("UNFOLLOW");
                        followEditTV.setOnClickListener(onUnFollowCLickListener);
                    }
                }
            });
        }
    };

    private View.OnClickListener onUnFollowCLickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MichTransport.getInstance().unfollow(UserDetailsActivity.this, user.getId(), new DoPostCallback<Void>() {
                @Override
                public void onLoad(int code, String message, Void data) {
                    if(code == MichTransport.LOAD_SUCCESS){
                        followEditTV.setText("FOLLOW");
                        followEditTV.setOnClickListener(onFollowCLickListener);
                    }
                }
            });
        }
    };

    private void loadUserPostImages() {
        MichTransport.getInstance().loadUserPosts(this, user.getId(), new DoPostCallback<ArrayList<PostResponse>>() {
            @Override
            public void onLoad(int code, String message, ArrayList<PostResponse> data) {
                if(code == MichTransport.LOAD_SUCCESS){
                    recyclerView.setLayoutManager(new GridLayoutManager(UserDetailsActivity.this,3));
                    recyclerView.setAdapter(new PostImagesRecyclerViewAdapter(UserDetailsActivity.this,data));
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_details;
    }
}
