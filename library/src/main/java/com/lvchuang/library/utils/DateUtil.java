package com.lvchuang.library.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by: Liu.ZhiYun on 2016/4/22.
 * Description:
 */
public class DateUtil {

    /**
     * 指定(yyyy-MM-dd HH:mm:ss)格式返回当前系统时间
     */
    public static String getDefaultDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
    /**
     * 指定格式返回当前系统时间
     */
    public static String getFormatDate(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    } /**
     * 返回当前系统时间(yyyyMMddHHmmss)时间戳格式
     */
    public static String getLabDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(new Date());
    }
}
