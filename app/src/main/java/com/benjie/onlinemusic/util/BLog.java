package com.benjie.onlinemusic.util;


import android.util.Log;

import com.benjie.onlinemusic.BuildConfig;

/**
 * Created by zhangfan on 2019/6/5.
 */
public class BLog {

    private static final String TAG = "BLog";
    private static final String KEY_ENABLE_LOG = "persist.debug.enable_log";
    private static final String VALUE_LOG_ON = "true";
    private static final String VALUE_LOG_OFF = "false";
    private static final boolean LOG_ENABLED = BuildConfig.DEBUG || SystemPropertiesUtils.getInstance().get(KEY_ENABLE_LOG, VALUE_LOG_OFF).equals(VALUE_LOG_ON);

    public static void d(String message) {
        if (LOG_ENABLED) {
            Log.d(TAG, message);
        }
    }

    public static void e(String message, Throwable e) {
        if (LOG_ENABLED) {
            Log.e(TAG, message, e);
        }
    }

    public static void e(Throwable e) {
        if (LOG_ENABLED) {
            Log.e(TAG, "", e);
        }
    }
}
