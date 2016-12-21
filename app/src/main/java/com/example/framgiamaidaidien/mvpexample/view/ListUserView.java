package com.example.framgiamaidaidien.mvpexample.view;

import com.example.framgiamaidaidien.mvpexample.model.User;

import java.util.List;

/**
 * Created by FRAMGIA\mai.dai.dien on 20/12/2016.
 */

public interface ListUserView{
    void reload();

    void showNoData();

    void showError();

    void displayListUser(List<User> listUser);
}
