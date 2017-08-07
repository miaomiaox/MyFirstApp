package com.xinluqishi.myfirstapp.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xinluqishi.myfirstapp.MainActivity;
import com.xinluqishi.myfirstapp.R;
import com.xinluqishi.myfirstapp.presenter.user.ILoginPresenter;
import com.xinluqishi.myfirstapp.ui.base.BaseActivity;
import com.xinluqishi.myfirstapp.ui.user.impl.LoginPresenterCompl;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by shikeyue on 2017/8/6.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements ILoginView {

    @ViewInject(R.id.et_login_username)
    private EditText editUsername;
    @ViewInject(R.id.et_login_password)
    private EditText editUserpass;

    @ViewInject(R.id.button_login)
    private Button btnLogin;
    @ViewInject(R.id.button_clear)
    private Button btnClear;

    ILoginPresenter loginPresenter;

    @ViewInject(R.id.login_progress)
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginPresenter = new LoginPresenterCompl(this);
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
    }

    @Event(R.id.button_login)
    private void loginButton(View view) {
        loginPresenter.setProgressBarVisiblity(View.VISIBLE);
        btnLogin.setEnabled(false);
        btnLogin.setEnabled(false);
        loginPresenter.doLogin(editUsername.getText().toString(), editUserpass.getText().toString());
    }

    @Override
    public void onClearText() {
        editUsername.setText("");
        editUserpass.setText("");
    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
        btnLogin.setEnabled(true);
        btnLogin.setEnabled(true);

        if (result){
            Toast.makeText(this,"Login Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        }
        else
            Toast.makeText(this,"Login Fail, code = " + code,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }


}
