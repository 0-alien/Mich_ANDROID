package com.mich.android.mich.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.koushikdutta.async.future.FutureCallback;
import com.mich.android.mich.R;
import com.mich.android.mich.Transport.MichTransport;
import com.mich.android.mich.Transport.Responses.RegisterResponse;

public class RegisterActivity extends AppCompatActivity {

    EditText etUserName;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViewsById();
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
