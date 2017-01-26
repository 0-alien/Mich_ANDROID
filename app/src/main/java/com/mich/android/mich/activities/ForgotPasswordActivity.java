package com.mich.android.mich.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mich.android.mich.BaseActivity;
import com.mich.android.mich.R;

public class ForgotPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.email_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPasswordActivity.this,PasswordResetByEmailActivity.class));
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forgot_password;
    }
}
