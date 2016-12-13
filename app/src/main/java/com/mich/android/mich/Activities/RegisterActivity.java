package com.mich.android.mich.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.koushikdutta.async.future.FutureCallback;
import com.mich.android.mich.BaseActivity;
import com.mich.android.mich.R;
import com.mich.android.mich.transport.MichTransport;
import com.mich.android.mich.transport.responses.RegisterResponse;

public class RegisterActivity extends BaseActivity {

    EditText etUserName;
    
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
    }


    public void onRegisterClick(View sender){
        Log.d("TAG",etUserName.getText().toString());
        String username = etUserName.getText().toString();

        FutureCallback<RegisterResponse> callback = new FutureCallback<RegisterResponse>() {
            @Override
            public void onCompleted(Exception e, RegisterResponse result) {

            }
        };
        MichTransport.getInstance().registerUser(this,callback,username);

    }

}
