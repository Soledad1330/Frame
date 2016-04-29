package com.lvchuang.demo.activity;

import com.lvchuang.demo.R;
import com.lvchuang.library.LCActivity;

/**
 * Created by: Liu.ZhiYun on 2016/4/27.
 * Description:起始页,做一些初始化操作及App欢迎页的过度
 */
public class StartActivity extends LCActivity {
    @Override
    public void setRootView() {
        setContentView(R.layout.aty_start);
    }

    @Override
    public void initData() {
        super.initData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2 * 1000);
                    skipActivity(StartActivity.this, GuideActivity.class);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
