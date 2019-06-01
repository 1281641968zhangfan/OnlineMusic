package com.benjie.onlinemusic.demo;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.benjie.onlinemusic.base.enums.FailedReason;
import com.benjie.onlinemusic.base.model.DataCallback;

/**
 * Created by zhangfan on 2019/6/2.
 */
public class DemoModel {

    private Handler mHandler = new Handler();

    public DemoModel() {
    }

    public void getData(final String param, @NonNull final DataCallback<String> callback) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (param) {
                    case "success":
                        callback.onSuccess("模拟请求成功。。。");
                        break;
                    case "failure":
                        callback.onFailure(FailedReason.REASON_UNKNOWN, "模拟请求失败，原因未知");
                        break;
                    default:
                        break;
                }
            }
        }, 1500);
    }

}
