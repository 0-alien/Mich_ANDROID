package com.mich.android.mich.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mich.android.mich.BaseActivity;
import com.mich.android.mich.R;

public class VsDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new VsDetailsAdapter());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vs_details;
    }


}
