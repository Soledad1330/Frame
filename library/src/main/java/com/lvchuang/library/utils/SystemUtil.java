package com.lvchuang.library.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * Created by: Liu.ZhiYun on 2016/4/22.
 * Description:系统信息工具类
 */
public class SystemUtil {
    /**
     * 获取手机IMEI码
     *
     * @param context
     * @return
     */
    public static String getPhoneIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 获取手机系统的SDK版本
     *
     * @return
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取系统版本
     *
     * @return
     */
    public static String getSystemVerison() {
        return Build.VERSION.RELEASE;
    }

    /**
     * p判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean checkNet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null;
    }

    /**
     * 判断手机WIFI是否连接
     *
     * @param context
     * @return
     */
    public static boolean isWIFI(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State state = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        return NetworkInfo.State.CONNECTED == state;
    }
    /**
     * 仅wifi联网功能是否开启
     */
    public static boolean checkOnlyWifi(Context context) {
        if (SharePreferUtil.readBoolean(context, ConfigManager.SETTING_FILE, ConfigManager.ONLY_WIFI)) {
            return isWIFI(context);
        } else {
            return true;
        }
    }

}
