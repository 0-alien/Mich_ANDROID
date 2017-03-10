package com.mich.android.mich.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.koushikdutta.async.future.FutureCallback;
import com.mich.android.mich.BaseActivity;
import com.mich.android.mich.R;
import com.mich.android.mich.transport.DoPostCallback;
import com.mich.android.mich.transport.MichTransport;
import com.mich.android.mich.transport.responses.RegisterResponse;

import java.text.BreakIterator;

public class RegisterActivity extends BaseActivity {

    EditText etUserName;
    EditText etPassword;
    EditText etEmail;
    EditText etFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewsById();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    private void initViewsById() {
        etUserName = (EditText) findViewById(R.id.et_user_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        etEmail = (EditText) findViewById(R.id.et_email);
        etFullName = (EditText) findViewById(R.id.et_full_name);
    }


    public void onRegisterClick(View sender){
        Log.d("TAG",etUserName.getText().toString());
        String username = etUserName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String name = etFullName.getText().toString();

        MichTransport.getInstance().register(this, username,email, password,name, new DoPostCallback<Void>() {
            @Override
            public void onLoad(int code, String message, Void data) {
                finish();
            }
        });



    }

}
