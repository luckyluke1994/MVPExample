package com.example.framgiamaidaidien.mvpexample.presenter;

import android.support.annotation.NonNull;

import com.example.framgiamaidaidien.mvpexample.model.ListUserHelper;
import com.example.framgiamaidaidien.mvpexample.model.OnLoadUserResult;
import com.example.framgiamaidaidien.mvpexample.model.User;
import com.example.framgiamaidaidien.mvpexample.view.ListUserView;

import java.util.List;

/**
 * Created by FRAMGIA\mai.dai.dien on 21/12/2016.
 */

public class ListUserPresenter implements OnLoadUserResult {
    private ListUserView view;
    private ListUserHelper model;

    public ListUserPresenter(ListUserView view, ListUserHelper model) {
        this.view = view;
        this.model = model;
        this.model.setOnLoadUserResult(this);
    }

    public void getData(boolean useCache) {
        view.reload();
        model.getUserList(useCache);
    }

    public void reload() {
        getData(false);
    }

    @Override
    public void onLoadUserSuccess(@NonNull List<User> userList) {
        if (userList.size() > 0)
            view.displayListUser(userList);
        else view.showNoData();
    }

    @Override
    public void onLoadUserError() {
        view.showError();
    }
}
