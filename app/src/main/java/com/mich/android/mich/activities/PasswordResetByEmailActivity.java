package com.mich.android.mich.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mich.android.mich.BaseActivity;
import com.mich.android.mich.R;
import com.mich.android.mich.transport.DoPostCallback;
import com.mich.android.mich.transport.MichTransport;
import com.mich.android.mich.transport.responses.LoginResponse;

public class PasswordResetByEmailActivity extends BaseActivity {

    EditText etEmail, etValidationCode, etNewPassword;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        etEmail = (EditText)findViewById(R.id.et_enter_email);
        etValidationCode = (EditText)findViewById(R.id.et_enter_validation_code);
        etNewPassword = (EditText)findViewById(R.id.et_enter_new_password);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_reset_by_email;
    }

    public void onNextClick(View sender){
        if(token == null){
            MichTransport.getInstance().sendRecovery(this, etEmail.getText().toString(), new DoPostCallback<LoginResponse>() {
                @Override
                public void onLoad(int code, String message, LoginResponse data) {
                    token = data.getToken();
                    etValidationCode.setVisibility(View.VISIBLE);
                    etNewPassword.setVisibility(View.VISIBLE);

                }
            });
        } else {
            MichTransport.getInstance().checkCode(this, token,etValidationCode.getText().toString(), new DoPostCallback<Void>() {
                @Override
                public void onLoad(int code, String message, Void data) {
                    if( code == MichTransport.LOAD_SUCCESS ){

                        MichTransport.getInstance().recover(PasswordResetByEmailActivity.this, token, etNewPassword.getText().toString(), new DoPostCallback<Void>() {
                            @Override
                            public void onLoad(int code, String message, Void data) {
                                if( code == MichTransport.LOAD_SUCCESS ){
                                    PasswordResetByEmailActivity.this.finish();
                                }
                            }
                        });

                    }
                }
            });
        }
    }

}
