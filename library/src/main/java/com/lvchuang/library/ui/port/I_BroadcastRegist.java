package com.lvchuang.library.ui.port;

/**
 * Created by: Liu.ZhiYun on 2016/4/22.
 * Description:规范Activity中广播接收者的注册/注销  协议;
 */
public interface I_BroadcastRegist {
    /**
     * 注册广播
     */
    void registBroadcast();

    /**
     * 解注广播
     */
    void unRegistBroadcast();
}
