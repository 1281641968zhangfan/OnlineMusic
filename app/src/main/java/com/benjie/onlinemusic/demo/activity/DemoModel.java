package com.benjie.onlinemusic.demo.activity;

import android.support.annotation.NonNull;

import com.benjie.onlinemusic.base.enums.FailedReason;
import com.benjie.onlinemusic.base.model.BaseModel;
import com.benjie.onlinemusic.base.model.DataCallback;

/**
 * Created by zhangfan on 2019/6/2.
 */
public class DemoModel extends BaseModel {

    public void getData(final String param, @NonNull final DataCallback<String> callback) {
        if (!isConnect) {
            // model 未连接，do nothing
            return;
        }
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
