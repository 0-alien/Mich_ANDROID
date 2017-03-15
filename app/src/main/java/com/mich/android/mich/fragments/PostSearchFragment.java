package com.mich.android.mich.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mich.android.mich.R;
import com.mich.android.mich.transport.DoPostCallback;
import com.mich.android.mich.transport.MichTransport;
import com.mich.android.mich.transport.responses.PostResponse;
import com.mich.android.mich.transport.responses.UserResponse;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link PostSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostSearchFragment extends Fragment implements TextWatcher {


    private RecyclerView recyclerView;
    private View view;
    private Context context;
    private static ArrayList<PostResponse> explorePostsCache;
    private static ArrayList<UserResponse> userSearchCache;
    private boolean searching = false;

    public PostSearchFragment() {
    }

    public static PostSearchFragment newInstance() {
        PostSearchFragment fragment = new PostSearchFragment();
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
        view = inflater.inflate(R.layout.fragment_post_search, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        context = view.getContext();
        setExplorePage();
        loadExplorePosts();
        return view;
    }

    private void loadExplorePosts() {
        MichTransport.getInstance().explore(context, new DoPostCallback<ArrayList<PostResponse>>() {
            @Override
            public void onLoad(int code, String message, ArrayList<PostResponse> data) {
                if(code == MichTransport.LOAD_SUCCESS){
                    explorePostsCache = data;
                    if(!searching) {
                        setExplorePage();
                    }
                }
            }
        });
    }

    private void setExplorePage(){
        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        recyclerView.setAdapter(new PostImagesRecyclerViewAdapter(context,explorePostsCache));
    }


    private void setSearchPage(){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new UserSearchAdapter(context, userSearchCache));
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String string = s.toString();
        if(string.equals("")){
            searching = false;
            setExplorePage();
        }else {
            searching = true;
            setSearchPage();
            loadSearchingList(string);
        }



    }

    private void loadSearchingList(String string) {

        MichTransport.getInstance().searchUsers(context, string, new DoPostCallback<ArrayList<UserResponse>>() {
            @Override
            public void onLoad(int code, String message, ArrayList<UserResponse> data) {
                PostSearchFragment.userSearchCache = data;
                setSearchPage();
            }
        });
    }
}
