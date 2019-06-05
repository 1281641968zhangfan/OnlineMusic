package com.benjie.onlinemusic.base.presenter;

import com.benjie.onlinemusic.base.model.BaseModel;
import com.benjie.onlinemusic.base.view.BaseView;

/**
 * Created by zhangfan on 2019/6/1.
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {

    private V mView;
    protected M mModel;

    public BasePresenter() {
        mModel = onCreateModel();
    }

    protected abstract M onCreateModel();

    public void attachView(V view) {
        this.mView = view;
        if (mModel != null) {
            mModel.connect();
        }
    }

    public void detachView(V view) {
        this.mView = null;
        if (mModel != null) {
            mModel.disconnect();
        }
    }

    public final boolean isViewAttached() {
        return mView != null;
    }

    public final V getView() {
        return mView;
    }
}
