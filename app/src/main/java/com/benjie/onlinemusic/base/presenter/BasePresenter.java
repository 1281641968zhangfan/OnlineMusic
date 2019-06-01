package com.benjie.onlinemusic.base.presenter;

import com.benjie.onlinemusic.base.view.BaseView;

/**
 * Created by zhangfan on 2019/6/1.
 */
public class BasePresenter<V extends BaseView> {

    private V mView;

    public void attachView(V view) {
        this.mView = view;
    }

    public void detachView(V view) {
        this.mView = null;
    }

    public final boolean isViewAttached() {
        return mView != null;
    }

    public final V getView() {
        return mView;
    }
}
