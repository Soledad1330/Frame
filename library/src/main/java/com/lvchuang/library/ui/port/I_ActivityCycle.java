package com.lvchuang.library.ui.port;

import android.view.View;

/**
 * Created by: Liu.ZhiYun on 2016/4/22.
 * Description:规范 Activity的生命周期与逻辑流程  协议;
 */
public interface I_ActivityCycle {

    final int RESUME = 1;
    final int PAUSE = 2;
    final int STOP = 3;
    final int DESTORY = 0;

    /**
     * 设置root界面
     */
    void setRootView();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 在新开线程初始化数据
     */
    void initDataInThread();

    /**
     * 初始化控件
     */
    void initWidget();

    /**
     * 点击事件回调
     */
    void widgetClick(View v);
}
