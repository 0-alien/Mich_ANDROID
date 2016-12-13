package com.mich.android.mich.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mich.android.mich.R;
import com.mich.android.mich.bean.Post;

public class PostSearchRecyclerViewAdapter  extends RecyclerView.Adapter<PostSearchRecyclerViewAdapter.ViewHolder> {




    @Override
    public PostSearchRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_post_search_recycler_view_item, parent, false);

        return new PostSearchRecyclerViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostSearchRecyclerViewAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        //        public final TextView mIdView;
//        public final TextView mContentView;
        public Post mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
//            mIdView = (TextView) view.findViewById(R.id.id);
//            mContentView = (TextView) view.findViewById(R.id.content);
        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
    }

}


