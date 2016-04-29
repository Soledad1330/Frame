package com.lvchuang.library;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.lvchuang.library.ui.base.LCActivityManager;
import com.lvchuang.library.ui.base.LCFragment;
import com.lvchuang.library.ui.base.LCFragmentV4;
import com.lvchuang.library.ui.port.I_ActivityCycle;
import com.lvchuang.library.ui.port.I_BroadcastRegist;
import com.lvchuang.library.ui.port.I_SkipActivity;
import com.lvchuang.library.utils.AnnotateUtil;
import com.lvchuang.library.utils.LogUtil;

import java.lang.ref.SoftReference;

/**
 * Created by: Liu.ZhiYun on 2016/4/22.
 * Description:本框架Activity的基类,所有Activity都必须继承此类
 */
public abstract class LCActivity extends FragmentActivity implements I_ActivityCycle, I_BroadcastRegist, I_SkipActivity, View.OnClickListener {
    public Activity mContext;

    protected LCFragment mCurrectFragment;

    protected LCFragmentV4 mCurrectFragmentV4;

    private static final int DATA_INITED_MSG = 0x1001;

    public int mActivityState = DESTORY;

    private final DataInitedHandler mHandler = new DataInitedHandler(this);

    private DataInitedThreadCallback mDataInitedCallback;

    private static class DataInitedHandler extends Handler {

        private final SoftReference<LCActivity> outerInstance;

        public DataInitedHandler(LCActivity outer) {
            outerInstance = new SoftReference<LCActivity>(outer);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == DATA_INITED_MSG && outerInstance.get() != null) {
                outerInstance.get().mDataInitedCallback.onSuccess();
            }
        }
    }

    private interface DataInitedThreadCallback {
        void onSuccess();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mContext = this;
        LCActivityManager.create().addActivity(this);
        LogUtil.state(this.getClass().getName(), "-----onCreate ");
        setRootView();
        AnnotateUtil.initBindView(this);
        initialize();
        registBroadcast();
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化操作(主要是为了代码结构简明)
     */
    private void initialize() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                initDataInThread();
                mHandler.sendEmptyMessage(DATA_INITED_MSG);
            }
        }).start();
        initData();
        initWidget();
    }


    @Override
    public void initData() {

    }

    @Override
    public void initDataInThread() {
        mDataInitedCallback = new DataInitedThreadCallback() {
            @Override
            public void onSuccess() {
                dataInitedInThread();
            }
        };
    }

    /**
     * 如果调用了initDataInThread(),,子线程数据初始化结束回调
     */
    protected void dataInitedInThread() {
    }

    @Override
    public void initWidget() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void registBroadcast() {

    }

    @Override
    public void unRegistBroadcast() {

    }

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.state(this.getClass().getName(), "-----onStart ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mActivityState = RESUME;
        LogUtil.state(this.getClass().getName(), "-----onResume ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mActivityState = PAUSE;
        LogUtil.state(this.getClass().getName(), "-----onPause ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mActivityState = STOP;
        LogUtil.state(this.getClass().getName(), "-----onStop ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.state(this.getClass().getName(), "-----onRestart ");
    }

    @Override
    protected void onDestroy() {
        unRegistBroadcast();
        mActivityState = DESTORY;
        LogUtil.state(this.getClass().getName(), "-----onDestroy ");
        super.onDestroy();
        LCActivityManager.create().finishActivity(this);
    }


    @Override
    public void skipActivity(Activity aty, Intent intent) {
        showActivity(aty, intent);
        aty.finish();
    }

    @Override
    public void skipActivity(Activity aty, Class<?> clazz) {
        showActivity(aty, clazz);
        aty.finish();
    }

    @Override
    public void skipActivity(Activity aty, Class<?> clazz, Bundle bundle) {
        showActivity(aty, clazz, bundle);
        aty.finish();
    }

    @Override
    public void showActivity(Activity aty, Intent intent) {
        aty.startActivity(intent);
    }

    @Override
    public void showActivity(Activity aty, Class<?> clazz) {
        Intent intent = new Intent();
        intent.setClass(aty, clazz);
        aty.startActivity(intent);
    }

    @Override
    public void showActivity(Activity aty, Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(aty, clazz);
        aty.startActivity(intent);
    }

    /**
     * 用Fragment替换视图
     *
     * @param resView        将要被替换掉的视图
     * @param targetFragment 用来替换的Fragment
     */
    public void changeFragment(int resView, LCFragment targetFragment) {
        if (targetFragment.equals(mCurrectFragment)) {
            return;
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.add(resView, targetFragment, targetFragment.getClass().getName());
        }
        if (targetFragment.isHidden()) {
            transaction.show(targetFragment);
            targetFragment.onChange();
        }
        if (mCurrectFragment != null && mCurrectFragment.isVisible()) {
            transaction.hide(mCurrectFragment);
        }
        mCurrectFragment = targetFragment;
        transaction.commit();
    }

    /**
     * 用Fragment替换视图
     *
     * @param resView        将要被替换掉的视图
     * @param targetFragment 用来替换的Fragment
     */
    public void changeFragment(int resView, LCFragmentV4 targetFragment) {
        if (targetFragment.equals(mCurrectFragmentV4)) {
            return;
        }
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.add(resView, targetFragment, targetFragment.getClass().getName());
        }
        if (targetFragment.isHidden()) {
            transaction.show(targetFragment);
            targetFragment.onChange();
        }
        if (mCurrectFragmentV4 != null && mCurrectFragmentV4.isVisible()) {
            transaction.hide(mCurrectFragmentV4);
        }
        mCurrectFragmentV4 = targetFragment;
        transaction.commit();
    }
}
