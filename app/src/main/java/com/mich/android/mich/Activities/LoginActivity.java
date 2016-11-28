package com.mich.android.mich.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.mich.android.mich.BaseActivity;
import com.mich.android.mich.R;


public class LoginActivity extends BaseActivity {

    private CallbackManager mFacebookCallbackManager;
    private LoginButton mFacebookSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mFacebookCallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        mFacebookSignInButton = (LoginButton)findViewById(R.id.facebook_sign_in_button);
        mFacebookSignInButton.registerCallback(mFacebookCallbackManager,fbLoginCallBack);

    }



    private FacebookCallback<LoginResult> fbLoginCallBack = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.d("TAG", "success");
            Log.d("TAG", loginResult.getAccessToken().getToken());
        }

        @Override
        public void onCancel() {
            Log.d("TAG", "cancel");
        }

        @Override
        public void onError(FacebookException error) {
            Log.d("TAG", "error");
            Log.d(LoginActivity.class.getCanonicalName(), error.getMessage());
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected int getLayoutId() {
        return NO_LAYOUT;
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
