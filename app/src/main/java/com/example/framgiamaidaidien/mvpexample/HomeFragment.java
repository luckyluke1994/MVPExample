package com.example.framgiamaidaidien.mvpexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by FRAMGIA\mai.dai.dien on 20/12/2016.
 */

public class HomeFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "luckyluke";
    private static final String mURL = "https://api.myjson.com/bins/44fql";
    private static final String EXTRA_MES = "extramess";
    private Button mAboutButton, mGetContentButton;
    private String mStringValue;

    public HomeFragment() {
        // required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate HomeFragment");
        super.onCreate(savedInstanceState);
        if (null != savedInstanceState) mStringValue = savedInstanceState.getString(EXTRA_MES);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGetContentButton = (Button) view.findViewById(R.id.get);
        mAboutButton      = (Button) view.findViewById(R.id.about);
        mAboutButton.setOnClickListener(this);
        mGetContentButton.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.about:
                showAbout();
                break;
            case R.id.get:
                showListUserScreen();
                break;
        }
    }

    private void showListUserScreen() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_layout,
                new ListUserFragment()).addToBackStack("ListUser").commit();
    }

    private void showAbout() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_layout,
                new AboutFragment()).addToBackStack("About").commit();
    }
}
