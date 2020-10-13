package com.monebac.com.ktolingbaic.java;

import com.monebac.com.R;
import com.monebac.com.base.BaseMvpActivity;
import com.monebac.com.wkyk.contract.LoginContract;
import com.monebac.com.wkyk.netutils.BaseResp;
import com.monebac.com.wkyk.presenter.LoginPresenter;

import org.jetbrains.annotations.NotNull;

public class JavaDemo extends BaseMvpActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {
    @NotNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }


    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initView() {

        //1000
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_new_login;
    }

    @Override
    public void onLoginSuccess(@NotNull BaseResp baseResp) {

    }

    @Override
    public void onLoginFail(@NotNull String str) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
