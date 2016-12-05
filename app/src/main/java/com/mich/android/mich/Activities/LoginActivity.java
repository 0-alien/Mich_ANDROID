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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.koushikdutta.async.future.FutureCallback;
import com.mich.android.mich.BaseActivity;
import com.mich.android.mich.R;
import com.mich.android.mich.transport.MichTransport;
import com.mich.android.mich.transport.requests.FacebookLoginRequest;
import com.mich.android.mich.transport.responses.LoginResponse;


public class LoginActivity extends BaseActivity {

    private static final int RC_SIGN_IN = 9001;

    private CallbackManager mFacebookCallbackManager;
    private LoginButton mFacebookSignInButton;
    private SignInButton mGoogleSignInButton;
    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mFacebookCallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        mFacebookSignInButton = (LoginButton)findViewById(R.id.facebook_sign_in_button);
        mFacebookSignInButton.registerCallback(mFacebookCallbackManager,fbLoginCallBack);


        mGoogleSignInButton = (SignInButton)findViewById(R.id.google_sign_in_button);
        mGoogleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });


    }

    private void signInWithGoogle() {
        if(mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        final Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private FacebookCallback<LoginResult> fbLoginCallBack = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            MichTransport.getInstance().
                    login(LoginActivity.this,loginCallback,new FacebookLoginRequest(loginResult.getAccessToken().getToken()));
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

    private FutureCallback<LoginResponse> loginCallback = new FutureCallback<LoginResponse>() {
        @Override
        public void onCompleted(Exception e, LoginResponse result) {
            if( e == null ){
                loginApp();
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(result.isSuccess()) {
                final GoogleApiClient client = mGoogleApiClient;
                Log.d("TAG",result.getSignInAccount().getId());
                //handleSignInResult(...)
            } else {
                //handleSignInResult(...);
            }
        } else {
            // Handle other values for requestCode
        }


    }

    @Override
    protected int getLayoutId() {
        return NO_LAYOUT;
    }

    public void loginAction(View sender) {
        loginApp();
    }

    public void loginApp(){
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
