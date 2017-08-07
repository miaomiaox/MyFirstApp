package com.xinluqishi.myfirstapp.presenter.user;

/**
 * Created by shikeyue on 17/8/7.
 */

public interface ILoginPresenter {
    void setProgressBarVisiblity(int invisible);

    void doLogin(String username, String password);
}
