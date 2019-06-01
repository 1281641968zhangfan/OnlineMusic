package com.benjie.onlinemusic.demo.activity;

import com.benjie.onlinemusic.base.enums.FailedReason;
import com.benjie.onlinemusic.base.model.DataCallback;
import com.benjie.onlinemusic.base.presenter.BasePresenter;

/**
 * Created by zhangfan on 2019/6/2.
 */
public class DemoPresenter extends BasePresenter<DemoView> {

    private DemoModel mModel;

    public DemoPresenter() {
        mModel = new DemoModel();
    }

    public void getData(String param) {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();
        mModel.getData(param, new DataCallback<String>() {
            @Override
            public void onSuccess(String data) {
                if (isViewAttached()) {
                    getView().hideLoading();
                    getView().showData(data);
                }
            }

            @Override
            public void onFailure(FailedReason reason, String messages) {
                if (isViewAttached()) {
                    getView().hideLoading();
                    getView().showError(reason, messages);
                }
            }
        });
    }
}
