package com.mich.android.mich.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mich.android.mich.R;

public class VsDetailsAdapter extends RecyclerView.Adapter<VsDetailsAdapter.ViewHolder> {


    @Override
    public int getItemViewType(int position) {
        if(position %2 == 0){
            return 1;
        }
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_vs_details_recycler_item_sent, parent, false);
        if(viewType == 1){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_vs_details_recycler_item_received, parent, false);
        }
        return new VsDetailsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VsDetailsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
