package com.mich.android.mich.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mich.android.mich.BaseActivity;
import com.mich.android.mich.R;

public class SplashScreenActivity extends BaseActivity {


    // Splash screen timer
    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash_screen;
    }

}
