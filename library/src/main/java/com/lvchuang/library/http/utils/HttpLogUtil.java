package com.lvchuang.library.http.utils;

import android.util.Log;

/**
 * Created by: Liu.ZhiYun on 2016/4/25.
 * Description:
 */
public class HttpLogUtil {
    private static boolean debug = false;

    public static void e(String msg) {
        if (debug) {
            Log.e("OkHttp", msg);
        }
    }
}
