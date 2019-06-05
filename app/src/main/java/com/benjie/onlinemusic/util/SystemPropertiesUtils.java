package com.benjie.onlinemusic.util;

import java.lang.reflect.Method;

/**
 * Created by zhangfan on 2019/6/5.
 */
public class SystemPropertiesUtils {
    private static volatile SystemPropertiesUtils singleton = null;
    private static Class mClazz;
    private static Method setProp;
    private static Method getProp;

    private SystemPropertiesUtils() {
    }

    public static SystemPropertiesUtils getInstance() {
        if (singleton == null) {
            synchronized (SystemPropertiesUtils.class) {
                if (singleton == null) {
                    singleton = new SystemPropertiesUtils();
                    try {
                        mClazz = Class.forName("android.os.SystemProperties");
                        setProp = mClazz.getDeclaredMethod("set", new Class[]{String.class, String.class});
                        getProp = mClazz.getDeclaredMethod("get", new Class[]{String.class, String.class});
                        setProp.setAccessible(true);
                        getProp.setAccessible(true);
                    } catch (Exception var3) {
                        var3.printStackTrace();
                    }
                }
            }
        }
        return singleton;
    }

    public void set(String name, String val) {
        try {
            setProp.invoke(mClazz, new Object[]{name, val});
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    public String get(String name, String def) {
        String result = null;
        try {
            result = (String) getProp.invoke(mClazz, new Object[]{name, def});
        } catch (Exception var5) {
            var5.printStackTrace();
        }
        return result;
    }
}