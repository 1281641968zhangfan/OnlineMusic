package com.benjie.onlinemusic.demo.fragment;

import com.benjie.onlinemusic.base.enums.FailedReason;
import com.benjie.onlinemusic.base.model.DataCallback;
import com.benjie.onlinemusic.base.presenter.BasePresenter;

/**
 * Created by zhangfan on 2019/6/2.
 */
public class DemoFramePresenter extends BasePresenter<DemoFrameView> {

    private DemoFrameModel mModel;

    public DemoFramePresenter() {
        mModel = new DemoFrameModel();
    }

    public void getNewUrl() {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();
        mModel.getNewUrl(new DataCallback<String>() {
            @Override
            public void onSuccess(String data) {
                getView().hideLoading();
                getView().updateNetUrl(data);
            }

            @Override
            public void onFailure(FailedReason reason, String messages) {
                getView().hideLoading();
                getView().showError(reason, messages);
            }
        });
    }
}
