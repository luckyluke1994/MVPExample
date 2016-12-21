package com.example.framgiamaidaidien.mvpexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.framgiamaidaidien.mvpexample.adapter.ListUserAdapter;
import com.example.framgiamaidaidien.mvpexample.model.ListUserHelper;
import com.example.framgiamaidaidien.mvpexample.model.User;
import com.example.framgiamaidaidien.mvpexample.presenter.ListUserPresenter;
import com.example.framgiamaidaidien.mvpexample.view.ListUserView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by FRAMGIA\mai.dai.dien on 20/12/2016.
 */

public class ListUserFragment extends Fragment implements ListUserView, View.OnClickListener{
    private static final String EXTRA_USER_LIST = "extra_user_list";
    private static final String URL = "https://api.myjson.com/bins/3w62s";

    private ArrayList<User> mUserList;
    private TextView mTVMessage;
    private ProgressBar mPBLoading;
    private RecyclerView mRecyclerView;
    private Button mBTReload;
    private ListUserAdapter mAdapter;

    // MVP
    private ListUserPresenter presenter;
    private ListUserHelper listUserHelper;


    public ListUserFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState != null) {
            mUserList = savedInstanceState.getParcelableArrayList(EXTRA_USER_LIST);
        }
        if (presenter == null) {
            Log.d("PJ3", "Presenter Null");
            if (listUserHelper == null)
                listUserHelper = new ListUserHelper(URL);
            listUserHelper.setList(mUserList);
            presenter = new ListUserPresenter(this, listUserHelper);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_user_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTVMessage = (TextView) view.findViewById(R.id.message);
        mPBLoading = (ProgressBar) view.findViewById(R.id.loading);
        mBTReload = (Button) view.findViewById(R.id.reload);
        mBTReload.setOnClickListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter != null) presenter.getData(true); // Use cache
    }

    @Override
    public void reload() {
        mPBLoading.setVisibility(View.VISIBLE);
        mTVMessage.setVisibility(View.GONE);
        mBTReload.setVisibility(View.GONE);
    }

    @Override
    public void showNoData() {
        mPBLoading.setVisibility(View.GONE);
        mTVMessage.setVisibility(View.VISIBLE);
        mBTReload.setVisibility(View.VISIBLE);
        mTVMessage.setText("No Data");
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        mPBLoading.setVisibility(View.GONE);
        mTVMessage.setVisibility(View.VISIBLE);
        mTVMessage.setText("Load Data Error");
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void displayListUser(List<User> listUser) {
        this.mUserList = (ArrayList<User>) listUser;
        mPBLoading.setVisibility(View.GONE);
        mTVMessage.setVisibility(View.GONE);
        mAdapter = new ListUserAdapter(listUser);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reload:
                presenter.reload();
                break;
        }
    }

    @Override
    public void onPause() {
        Log.d("PJ3", "On Pause");
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.d("PJ3", "On Destroy");
        super.onDestroy();
        presenter = null;
        listUserHelper = null;
        mAdapter = null;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("PJ3", "On save Instance State");
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_USER_LIST, mUserList);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reload:
                presenter.getData(false); // Not use cache
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
