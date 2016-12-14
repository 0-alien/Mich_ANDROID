package com.mich.android.mich.activities;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.mich.android.mich.BaseActivity;
import com.mich.android.mich.R;
import com.mich.android.mich.bean.Post;
import com.mich.android.mich.fragments.CameraFragment;
import com.mich.android.mich.fragments.MyProfileFragment;
import com.mich.android.mich.fragments.PostSearchFragment;
import com.mich.android.mich.fragments.PostsFragment;
import com.mich.android.mich.fragments.VsFragment;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        PostsFragment.OnListFragmentInteractionListener{


    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    Toolbar toolbar;
    List<ImageView> tabButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (CameraFragment.mCamera == null){
            CameraFragment.mCamera = Camera.open(0);
        }
        setSupportActionBar(toolbar);


        initDrawerLayout();
        initViewPager();
        initTabButtons();
    }

    private void initTabButtons() {
        tabButtons = new ArrayList<ImageView>();
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
                    mViewPager.setCurrentItem(Integer.parseInt(v.getTag().toString()));
                }
            });
        }
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

    private void initViewPager() {
        mDemoCollectionPagerAdapter =
                new DemoCollectionPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

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

    public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i){
                case 0:
                    return PostsFragment.newInstance(1);
                case 1:
                    return VsFragment.newInstance();
                case 2:
                    return CameraFragment.newInstance();
                case 3:
                    return PostSearchFragment.newInstance();
                case 4:
                    return MyProfileFragment.newInstance();

            }
            return PostsFragment.newInstance(1);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }
    }

}
