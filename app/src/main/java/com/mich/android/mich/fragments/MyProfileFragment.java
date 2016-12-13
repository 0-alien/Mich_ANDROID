package com.mich.android.mich.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mich.android.mich.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MyProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfileFragment extends Fragment {


    public MyProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MyProfileFragment.
     */
    public static MyProfileFragment newInstance() {
        MyProfileFragment fragment = new MyProfileFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        Context context = view.getContext();

        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        recyclerView.setAdapter(new MyProfileRecyclerViewAdapter());
        return view;
    }


}
