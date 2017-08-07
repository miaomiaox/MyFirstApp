package com.xinluqishi.myfirstapp.ui.user.impl;

import android.os.Handler;

import com.xinluqishi.myfirstapp.bean.user.IUser;
import com.xinluqishi.myfirstapp.presenter.user.ILoginPresenter;
import com.xinluqishi.myfirstapp.ui.user.ILoginView;

/**
 * Created by shikeyue on 17/8/7.
 */
public class LoginPresenterCompl implements ILoginPresenter {

    ILoginView iLoginView;
    IUser user;
    Handler handler;

    public LoginPresenterCompl(ILoginView iLoginView) {

    }

    @Override
    public void setProgressBarVisiblity(int invisible) {

    }

    @Override
    public void doLogin(String username, String password) {

    }
}
