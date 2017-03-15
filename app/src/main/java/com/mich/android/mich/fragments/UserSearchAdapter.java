package com.mich.android.mich.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mich.android.mich.R;
import com.mich.android.mich.Utils;
import com.mich.android.mich.activities.UserDetailsActivity;
import com.mich.android.mich.transport.responses.UserResponse;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserSearchAdapter extends RecyclerView.Adapter<UserSearchAdapter.ViewHolder> {

    Context context;
    ArrayList<UserResponse> users;

    public UserSearchAdapter(Context context, ArrayList<UserResponse> users) {
        this.context = context;
        this.users = users;
        if(users == null){
            this.users = new ArrayList<UserResponse>();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_search_list_item, parent, false);
        return new UserSearchAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Utils.loadUrlInImageView(context, holder.img, users.get(position).getAvatar());
        holder.name.setText(users.get(position).getName());
        holder.userName.setText( "@"+users.get(position).getUsername() );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserDetailsActivity.class);
                Bundle b = new Bundle();
                new Gson();
                b.putString("userJSON", new Gson().toJson(users.get(position)));
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView name;
        TextView userName;
        ViewHolder(View itemView) {
            super(itemView);
            this.img = (CircleImageView)itemView.findViewById(R.id.img);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.userName = (TextView) itemView.findViewById(R.id.username);
        }



    }
}
