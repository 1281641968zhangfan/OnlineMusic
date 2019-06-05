package com.benjie.onlinemusic.base.model;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.CallSuper;

/**
 * Created by zhangfan on 2019/6/2.
 */
public abstract class BaseModel implements Handler.Callback {

    protected boolean isConnect = false;
    protected Handler mHandler;

    public BaseModel() {
        mHandler = new Handler(this);
    }

    @CallSuper
    public void connect() {
        if (!isConnect) {
            isConnect = true;
        }
    }

    @CallSuper
    public void disconnect() {
        if (isConnect) {
            isConnect = false;
        }
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public final boolean handleMessage(Message msg) {
        if (!isConnect) {
            // do nothing
            return true;
        }
        return executeMessage(msg);
    }

    protected boolean executeMessage(Message msg) {
        return false;
    }
}