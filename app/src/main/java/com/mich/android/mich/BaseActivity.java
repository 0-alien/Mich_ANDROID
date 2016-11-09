package com.mich.android.mich;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    protected int NO_LAYOUT = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getLayoutId() != NO_LAYOUT){
            setContentView(getLayoutId());
        }
    }

    protected abstract int getLayoutId();


}
