package com.mich.android.mich.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mich.android.mich.R;

/**
 * Created by imac on 12/26/16.
 */

public class NotificationsAdapter  extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder>{


    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notifications_list_item, parent, false);

        return new NotificationsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificationsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;


        public ViewHolder(View view) {
            super(view);
            mView = view;
        }

    }


}
