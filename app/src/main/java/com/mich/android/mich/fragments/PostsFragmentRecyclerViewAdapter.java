package com.mich.android.mich.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mich.android.mich.R;
import com.mich.android.mich.bean.Post;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Post} and makes a call to the
 * specified {@link PostsFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PostsFragmentRecyclerViewAdapter extends RecyclerView.Adapter<PostsFragmentRecyclerViewAdapter.ViewHolder> {

    private final List<Post> mValues;
    private final PostsFragment.OnListFragmentInteractionListener mListener;

    public PostsFragmentRecyclerViewAdapter(List<Post> items, PostsFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_posts_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onCommentClick(holder.mItem);
                }
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView commentsBtn;
//        public final TextView mContentView;
        public Post mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            commentsBtn = (ImageView)view.findViewById(R.id.comments_btn);
        }
    }
}
