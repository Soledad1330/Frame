package com.lvchuang.library.http.utils;

/**
 * Created by: Liu.ZhiYun on 2016/4/25.
 * Description:
 */
public class Exceptions
{
    public static void illegalArgument(String msg, Object... params)
    {
        throw new IllegalArgumentException(String.format(msg, params));
    }


}

