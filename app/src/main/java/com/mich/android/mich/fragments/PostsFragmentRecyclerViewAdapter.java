package com.mich.android.mich.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mich.android.mich.R;
import com.mich.android.mich.Utils;
import com.mich.android.mich.activities.UserDetailsActivity;
import com.mich.android.mich.transport.responses.PostResponse;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PostResponse} and makes a call to the
 * specified {@link PostsFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PostsFragmentRecyclerViewAdapter extends RecyclerView.Adapter<PostsFragmentRecyclerViewAdapter.ViewHolder> {

    private final List<PostResponse> mValues;
    private final PostsFragment.OnListFragmentInteractionListener mListener;
    private final Context context;

    public PostsFragmentRecyclerViewAdapter(Context context, List<PostResponse> items, PostsFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.context = context;
    }

    public void setPostResponses(List<PostResponse> postResponses){
        this.mValues.clear();
        this.mValues.addAll(postResponses);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_posts_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);

        holder.commentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onCommentClick(holder.mItem);
                }
            }
        });
        holder.likesTv.setText(holder.mItem.getLikes()+"");
        holder.titleTv.setText(holder.mItem.getTitle());
        Utils.loadUrlInImageView(context,holder.postCoverImg,holder.mItem.getImage());
        holder.likesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.userUpperView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserDetailsActivity.class);
                Bundle b = new Bundle();
                new Gson();
                b.putInt("userID", mValues.get(position).getUserid());
                b.putBoolean("withID", true);
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });

        if (position == 0) {
            holder.mView.findViewById(R.id.top_space).setVisibility(View.VISIBLE);
        } else {
            holder.mView.findViewById(R.id.top_space).setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final ImageView commentsBtn;
        final ImageView likesBtn;
        final TextView likesTv;
        final TextView titleTv;
        final ImageView postCoverImg;
        final View userUpperView;
        PostResponse mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            likesBtn = (ImageView)view.findViewById(R.id.likes_btn);
            commentsBtn = (ImageView)view.findViewById(R.id.comments_btn);
            likesTv = (TextView)view.findViewById(R.id.likes_tv);
            titleTv = (TextView)view.findViewById(R.id.tv_title);
            postCoverImg = (ImageView)view.findViewById(R.id.img_post_cover);
            userUpperView = view.findViewById(R.id.user_upper_view);
        }
    }
}
