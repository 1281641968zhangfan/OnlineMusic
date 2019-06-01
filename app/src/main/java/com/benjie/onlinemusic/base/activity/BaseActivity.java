package com.benjie.onlinemusic.base.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.benjie.onlinemusic.base.view.BaseView;

/**
 * Created by zhangfan on 2019/6/1.
 */
public abstract class BaseActivity extends Activity implements BaseView {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        obtainDataFromMemory(savedInstanceState);
        setContentView(getLayoutId());
        initViews();
        initViewListeners();
        initPresenter();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveDataToMemory(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        saveDataToMemory(savedInstanceState);
    }

    @Override
    public void showLoading() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected abstract void initViewListeners();

    protected void saveDataToMemory(Bundle outState) {}

    protected void obtainDataFromMemory(Bundle savedInstanceState) {}

}
