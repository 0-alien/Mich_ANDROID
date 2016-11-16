package com.mich.android.mich.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mich.android.mich.BaseActivity;
import com.mich.android.mich.R;



public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    public void loginAction(View sender) {
        Log.d("MICH_TAG", "login");
        startActivity(new Intent(LoginActivity.this,NavigationActivity.class));
        finish();
    }

    public void onRegisterClick(View sender) {
        Log.d("MICH_TAG", "login");
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        finish();
    }


}
