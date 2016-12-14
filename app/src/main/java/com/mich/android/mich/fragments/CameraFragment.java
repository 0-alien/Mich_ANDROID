package com.mich.android.mich.fragments;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mich.android.mich.R;
import com.mich.android.mich.customview.CameraPreview;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CameraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CameraFragment extends Fragment {

    private TextView libraryBtn;
    private TextView cameraBtn;

    public static Camera mCamera;
    private CameraPreview mPreview;

    public CameraFragment() {
        // Required empty public constructor
    }


    public static CameraFragment newInstance() {
        CameraFragment fragment = new CameraFragment();
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

        final View view = inflater.inflate(R.layout.fragment_camera, container, false);

        view.findViewById(R.id.library_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.gallery_view).setVisibility(View.VISIBLE);
                view.findViewById(R.id.camera_view).setVisibility(View.GONE);
            }
        });

        view.findViewById(R.id.camera_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.gallery_view).setVisibility(View.GONE);
                view.findViewById(R.id.camera_view).setVisibility(View.VISIBLE);
                // Create an instance of Camera
                if(mCamera == null) {
                    mCamera = Camera.open(0);
                }

                // Create our Preview view and set it as the content of our activity.
                if(mPreview == null) {
                    mPreview = new CameraPreview(getActivity(), mCamera);
                    FrameLayout preview = (FrameLayout) view.findViewById(R.id.camera_preview);
                    preview.addView(mPreview);
                }
            }
        });


        return view;
    }



}
