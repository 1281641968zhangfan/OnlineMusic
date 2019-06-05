package com.benjie.onlinemusic.demo.fragment;

import android.support.annotation.NonNull;

import com.benjie.onlinemusic.base.enums.FailedReason;
import com.benjie.onlinemusic.base.model.BaseModel;
import com.benjie.onlinemusic.base.model.DataCallback;

import java.util.Random;

/**
 * Created by zhangfan on 2019/6/2.
 */
public class DemoFrameModel extends BaseModel {

    private static final String[] URLS = {
            "www.baidu.com",
            "www.google.com",
            "www.github.com"
    };

    public void getNewUrl(@NonNull final DataCallback<String> callback) {
        if (!isConnect) {
            // model 未连接，do nothing
            return;
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int index = random.nextInt(URLS.length + 10);
                if (index >= URLS.length) {
                    callback.onFailure(FailedReason.REASON_OUT_OF_INDEX, "获取 URL 失败");
                } else {
                    callback.onSuccess(URLS[index]);
                }
            }
        }, 1500);
    }
}
