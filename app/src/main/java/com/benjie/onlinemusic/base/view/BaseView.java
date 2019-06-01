package com.benjie.onlinemusic.base.view;

import android.content.Context;

import com.benjie.onlinemusic.base.enums.FailedReason;

/**
 * Created by zhangfan on 2019/6/1.
 */
public interface BaseView {

    void initPresenter();

    void showLoading();

    void hideLoading();

    void showError(FailedReason reason, String msg);

    Context getContext();
}
