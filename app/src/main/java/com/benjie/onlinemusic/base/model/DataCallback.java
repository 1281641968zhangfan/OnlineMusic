package com.benjie.onlinemusic.base.model;

import com.benjie.onlinemusic.base.enums.FailedReason;

/**
 * Created by zhangfan on 2019/6/1.
 */
public interface DataCallback<T> {

    void onSuccess(T data);

    void onFailure(FailedReason reason, String messages);

}
