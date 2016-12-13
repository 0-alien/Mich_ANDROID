package com.mich.android.mich.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mich.android.mich.R;
import com.mich.android.mich.bean.Post;

public class VsFragmentRecyclerViewAdapter extends RecyclerView.Adapter<VsFragmentRecyclerViewAdapter.ViewHolder> {




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_vs_list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
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


