package com.lvchuang.library.utils;

import android.util.Log;

/**
 * Created by: Liu.ZhiYun on 2016/4/22.
 * Description:应用程序Log管理工具类
 */
public class LogUtil {
    public static boolean IS_DEBUG = true;

    public static boolean DEBUG_LOG = true;

    public static void state(String className, String state) {
        if (DEBUG_LOG) {
            Log.d("activity_state", className + state);
        }
    }

    public static void debug(String msg) {
        if(IS_DEBUG){
            Log.i("debug",msg);
        }
    }
}
