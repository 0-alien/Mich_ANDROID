package com.mich.android.mich.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mich.android.mich.App;
import com.mich.android.mich.R;
import com.mich.android.mich.Utils;
import com.mich.android.mich.transport.DoPostCallback;
import com.mich.android.mich.transport.MichTransport;
import com.mich.android.mich.transport.responses.PostResponse;
import com.mich.android.mich.transport.responses.UserResponse;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MyProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfileFragment extends Fragment {

    CircleImageView avatar;
    TextView editProfileTV;
    RecyclerView recyclerView;
    Context context;
    private View view;
    static ArrayList<PostResponse> postImagesCache = new ArrayList<>();


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
        view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        editProfileTV = (TextView)view.findViewById(R.id.edit_tv);
        context = view.getContext();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setAdapter(new PostImagesRecyclerViewAdapter(getActivity(),new ArrayList<PostResponse>()));
        initView();
        loadUserData();

        return view;
    }

    private void initView() {
        avatar = (CircleImageView) view.findViewById(R.id.img_avatar);
    }

    private void loadUserData() {

        MichTransport.getInstance().loadCurrentUserData(getActivity(), new DoPostCallback<UserResponse>() {
            @Override
            public void onLoad(int code, String message, UserResponse data) {
                if (code == MichTransport.LOAD_SUCCESS){
                    Utils.loadUrlInImageView(getActivity(),avatar,data.getAvatar());
                }
            }
        });

        loadUserPostImages();

    }

    private void loadUserPostImages() {
        MichTransport.getInstance().loadUserPosts(context, App.getInstance().getUserID(), new DoPostCallback<ArrayList<PostResponse>>() {
            @Override
            public void onLoad(int code, String message, ArrayList<PostResponse> data) {
                if(code == MichTransport.LOAD_SUCCESS){
                    postImagesCache = data;
                    recyclerView.setAdapter(new PostImagesRecyclerViewAdapter(context, postImagesCache));
                }
            }
        });
    }



}
