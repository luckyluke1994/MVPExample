package com.example.framgiamaidaidien.mvpexample.model;

import java.util.List;

/**
 * Created by FRAMGIA\mai.dai.dien on 20/12/2016.
 */

public interface OnLoadUserResult {
    public void onLoadUserSuccess(List<User> userList);

    public void onLoadUserError();

}
