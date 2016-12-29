package com.mich.android.mich.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mich.android.mich.BaseActivity;
import com.mich.android.mich.R;
import com.mich.android.mich.bean.Post;
import com.mich.android.mich.fragments.MyProfileFragment;
import com.mich.android.mich.fragments.PostSearchFragment;
import com.mich.android.mich.fragments.PostsFragment;
import com.mich.android.mich.fragments.VsFragment;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        PostsFragment.OnListFragmentInteractionListener{

    Toolbar toolbar;
    List<ImageView> tabButtons;
    FrameLayout fragmentPlaceholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initDrawerLayout();
        initTabButtons();
        selectPage(0);
    }

    private void initTabButtons() {
        fragmentPlaceholder = (FrameLayout)findViewById(R.id.fragment_placeholder);
        tabButtons = new ArrayList<>();
        tabButtons.add((ImageView)findViewById(R.id.btn_tab1));
        tabButtons.add((ImageView)findViewById(R.id.btn_tab2));
        tabButtons.add((ImageView)findViewById(R.id.btn_tab3));
        tabButtons.add((ImageView)findViewById(R.id.btn_tab4));
        tabButtons.add((ImageView)findViewById(R.id.btn_tab5));
        for (int i = 0; i < tabButtons.size(); i++){
            tabButtons.get(i).setTag(i);
            tabButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedPos = Integer.parseInt(v.getTag().toString());
                    selectPage(selectedPos);
                }
            });
        }


        findViewById(R.id.btn_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startImageActivity(true);
            }
        });

        findViewById(R.id.btn_open_gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startImageActivity(false);
            }
        });

    }


    private void startImageActivity(boolean isCamera){
        Intent intent = new Intent(NavigationActivity.this, ImageActivity.class);
        Bundle b = new Bundle();
        b.putBoolean("isCamera",isCamera);
        intent.putExtras(b);
        startActivity(intent);
    }


    private void selectPage(int selectedPos){
        if(selectedPos == 2){
            if(findViewById(R.id.gallery_photo_chooser).getVisibility() == View.VISIBLE){
                findViewById(R.id.gallery_photo_chooser).setVisibility(View.GONE);
            } else {
                findViewById(R.id.gallery_photo_chooser).setVisibility(View.VISIBLE);
            }
        }else {
            findViewById(R.id.gallery_photo_chooser).setVisibility(View.GONE);
            Fragment fragment = getFragmentFromPos(selectedPos);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_placeholder, fragment);
            ft.commit();
        }

    }


    private Fragment getFragmentFromPos(int pos){

        switch (pos){
            case 0:
                return PostsFragment.newInstance(1);
            case 1:
                return VsFragment.newInstance();
            case 3:
                return PostSearchFragment.newInstance();
            case 4:
                return MyProfileFragment.newInstance();
        }
        return null;

    }

    private void initDrawerLayout() {
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ImageView ivCustomDrawable = (ImageView) toolbar.findViewById(R.id.ivCustomDrawable);
        ivCustomDrawable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.END); //What ever your drawer gravity
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notifications) {
            startActivity(new Intent(this,NotificationsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_navigation;
    }

    @Override
    public void onCommentClick(Post post) {
        startActivity(new Intent(this,CommentsActivity.class));
    }



}
