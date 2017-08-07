package com.xinluqishi.myfirstapp.ui.user;

/**
 * Created by shikeyue on 17/8/7.
 */
public interface ILoginView {

    public void onClearText();

    public void onLoginResult(Boolean result, int code);

    public void onSetProgressBarVisibility(int visibility);

}
