package com.mich.android.mich.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mich.android.mich.R;
import com.mich.android.mich.Utils;
import com.mich.android.mich.activities.UserDetailsActivity;
import com.mich.android.mich.transport.DoPostCallback;
import com.mich.android.mich.transport.MichTransport;
import com.mich.android.mich.transport.responses.PostResponse;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
        Utils.loadUrlInImageView(context,holder.avatar,holder.mItem.getAvatar());
        setHolderClickListeners(holder,position);

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



    private void setHolderClickListeners(final ViewHolder holder, final int position) {
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


        holder.postCoverImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long before = 0;
                if(v.getTag() != null){
                    before = (long)v.getTag();
                }
                long current = System.currentTimeMillis();
                v.setTag(current);
                if(current - before < 200){
                    holder.fireImg.setVisibility(View.VISIBLE);
                    Animation a = new AlphaAnimation(1.00f, 0.00f);
                    a.setDuration(1000);
                    a.setAnimationListener(new Animation.AnimationListener() {
                        public void onAnimationStart(Animation animation) {}
                        public void onAnimationRepeat(Animation animation) {}
                        public void onAnimationEnd(Animation animation) {
                            holder.fireImg.setVisibility(View.INVISIBLE);
                        }
                    });
                    holder.fireImg.startAnimation(a);

                    MichTransport.getInstance().like(context, mValues.get(position).getId(), new DoPostCallback<Void>() {
                        @Override
                        public void onLoad(int code, String message, Void data) {
                            if(code == MichTransport.LOAD_SUCCESS){
                                mValues.get(position).setLikes( mValues.get(position).getLikes() + 1);
                                notifyDataSetChanged();
                            }
                        }
                    });



                }
            }
        });



    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final ImageView commentsBtn;
        final ImageView likesBtn;
        final TextView likesTv;
        final TextView titleTv;
        final ImageView postCoverImg;
        final ImageView fireImg;
        final View userUpperView;
        final CircleImageView avatar;
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
            fireImg = (ImageView)view.findViewById(R.id.img_fire);
            avatar = (CircleImageView)view.findViewById(R.id.img_avatar);
        }
    }
}
