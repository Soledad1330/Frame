package com.lvchuang.library.ui.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lvchuang.library.utils.AnnotateUtil;

import java.lang.ref.SoftReference;

/**
 * Created by: Liu.ZhiYun on 2016/4/22.
 * Description:Fragment(Support V4)基类,规范了Fragment的逻辑顺序与生命周期
 */
public abstract class LCFragmentV4 extends Fragment implements View.OnClickListener {

    private static final int DATA_INITED_MSG = 0x1002;

    protected View mFragmentRootView;

    private ThreadDataInitedCallback mDataInitedCallback;

    private final FragmentHandler mHandler = new FragmentHandler(this);

    private interface ThreadDataInitedCallback {
        void onSuccess();
    }

    private class FragmentHandler extends Handler {
        private final SoftReference<LCFragmentV4> outerInstance;

        public FragmentHandler(LCFragmentV4 outer) {
            outerInstance = new SoftReference<LCFragmentV4>(outer);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == DATA_INITED_MSG && outerInstance.get() != null) {
                outerInstance.get().mDataInitedCallback.onSuccess();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentRootView = inflaterView(inflater, container, savedInstanceState);
        AnnotateUtil.initBindView(this, mFragmentRootView);
        initData();
        initWidget(mFragmentRootView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                initDataInThread();
                mHandler.sendEmptyMessage(DATA_INITED_MSG);
            }
        });
        return mFragmentRootView;
    }


    protected void initData() {
    }

    protected void initWidget(View view) {
    }

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    protected void widgetClick(View v) {
    }

    protected void initDataInThread() {
        mDataInitedCallback = new ThreadDataInitedCallback() {
            @Override
            public void onSuccess() {
                dataInitedInThread();
            }
        };
    }

    /**
     * 如果调用了initDataFromThread()，则当数据初始化完成后将回调该方法。
     */
    protected void dataInitedInThread() {
    }

    protected abstract View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 当通过changeFragment()显示时会被调用(类似于onResume)
     */
    public void onChange() {
    }

    protected <T extends View> T bindView(int id) {
        return (T) mFragmentRootView.findViewById(id);
    }

    protected <T extends View> T bindView(int id, boolean click) {
        T view = (T) mFragmentRootView.findViewById(id);
        if (click) {
            view.setOnClickListener(this);
        }
        return view;
    }


}
