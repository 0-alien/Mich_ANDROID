package com.mich.android.mich.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.mich.android.mich.BaseActivity;
import com.mich.android.mich.R;
import com.mich.android.mich.Utils;
import com.mich.android.mich.fragments.MyProfileFragment;
import com.mich.android.mich.fragments.PostSearchFragment;
import com.mich.android.mich.fragments.PostsFragment;
import com.mich.android.mich.fragments.VsFragment;
import com.mich.android.mich.transport.DoPostCallback;
import com.mich.android.mich.transport.MichTransport;
import com.mich.android.mich.transport.responses.PostResponse;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        PostsFragment.OnListFragmentInteractionListener, View.OnClickListener{

    Toolbar toolbar;
    List<ImageView> tabButtons;
    FrameLayout fragmentPlaceholder;
    LinearLayout logoutBtn;
    private EditText searchET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initDrawerLayout();
        initTabButtons();
        selectPage(0);
        searchET = (EditText)findViewById(R.id.search_view_et);
        logoutBtn = (LinearLayout)findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(this);

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
        searchET.setText("");
        if(selectedPos == 3){
            findViewById(R.id.search_view).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.search_view).setVisibility(View.INVISIBLE);
        }
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



            tabButtons.get(0).setImageResource(R.drawable.ic_home_tab_inactive);
            tabButtons.get(1).setImageResource(R.drawable.ic_vs_tab_inactive);
            tabButtons.get(3).setImageResource(R.drawable.ic_search_tab_inactive);
            tabButtons.get(4).setImageResource(R.drawable.ic_profile_tab_inactive);
            switch (selectedPos){
                case 0:
                    tabButtons.get(0).setImageResource(R.drawable.ic_home_tab);
                    break;
                case 1:
                    tabButtons.get(1).setImageResource(R.drawable.ic_vs_tab);
                    break;
                case 3:
                    tabButtons.get(3).setImageResource(R.drawable.ic_search_tab);
                    break;
                case 4:
                    tabButtons.get(4).setImageResource(R.drawable.ic_profile_tab);
                    break;
            }

        }

    }


    private Fragment getFragmentFromPos(int pos){

        switch (pos){
            case 0:
                return PostsFragment.newInstance(1);
            case 1:
                return VsFragment.newInstance();
            case 3:
                PostSearchFragment fr = PostSearchFragment.newInstance();
                searchET.addTextChangedListener(fr);
                searchET.setImeOptions(EditorInfo.IME_ACTION_DONE);
                return fr;
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
    public void onCommentClick(PostResponse postResponse) {
        startActivity(new Intent(this,CommentsActivity.class));
    }


    @Override
    public void onClick(View v) {
        if (v == logoutBtn){
            finish();
            MichTransport.getInstance().logout(this, new DoPostCallback<Void>() {
                @Override
                public void onLoad(int code, String message, Void data) {
                    Toast.makeText(NavigationActivity.this, "logged out" ,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
