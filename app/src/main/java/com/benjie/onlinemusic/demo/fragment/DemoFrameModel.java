package com.benjie.onlinemusic.demo.fragment;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.benjie.onlinemusic.base.enums.FailedReason;
import com.benjie.onlinemusic.base.model.DataCallback;

import java.util.Random;

/**
 * Created by zhangfan on 2019/6/2.
 */
public class DemoFrameModel {

    private Handler mHandler = new Handler();
    String[] urls = {
            "www.baidu.com",
            "www.google.com",
            "www.github.com"
    };

    public void getNewUrl(@NonNull final DataCallback<String> callback) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int index = random.nextInt(urls.length + 10);
                if (index >= urls.length) {
                    callback.onFailure(FailedReason.REASON_OUT_OF_INDEX, "获取 URL 失败");
                } else {
                    callback.onSuccess(urls[index]);
                }
            }
        }, 1500);
    }
}
