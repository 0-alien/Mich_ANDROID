package com.mich.android.mich.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mich.android.mich.R;
import com.mich.android.mich.Utils;
import com.mich.android.mich.transport.responses.PostResponse;

import java.util.ArrayList;

public class PostImagesRecyclerViewAdapter extends RecyclerView.Adapter<PostImagesRecyclerViewAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<PostResponse> posts;

    public PostImagesRecyclerViewAdapter(Context context, ArrayList<PostResponse> posts){
        this.context = context;
        this.posts = posts;

        if(posts == null){
            this.posts = new ArrayList<>();
        }
    }


    @Override
    public PostImagesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_post_image_recycler_view_item, parent, false);

        return new PostImagesRecyclerViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostImagesRecyclerViewAdapter.ViewHolder holder, int position) {
        Utils.loadUrlInImageView(context, holder.img, posts.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final ImageView img;

        ViewHolder(View view) {
            super(view);
            mView = view;
            img = (ImageView)view.findViewById(R.id.img);
        }
    }

}


