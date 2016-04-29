package com.lvchuang.library.ui.base;

import android.app.Activity;
import android.content.Context;

import com.lvchuang.library.LCActivity;
import com.lvchuang.library.ui.port.I_ActivityCycle;

import java.util.Stack;

/**
 * Created by: Liu.ZhiYun on 2016/4/22.
 * Description:程序Activity管理类,用于Activity的管理与App的退出
 */
public class LCActivityManager {

    private static final LCActivityManager mInstance = new LCActivityManager();

    private Stack<I_ActivityCycle> mActivityStack;

    public static LCActivityManager create() {
        return mInstance;
    }

    /**
     * 获取当前Activity栈中元素个数
     */
    public int getCount() {
        return mActivityStack.size();
    }

    public void addActivity(I_ActivityCycle activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<I_ActivityCycle>();
        }
        mActivityStack.add(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 获取当前Activity（栈顶Activity） 没有找到则返回null
     */
    public Activity findActivity(Class<?> cls) {
        I_ActivityCycle activity = null;
        for (I_ActivityCycle aty : mActivityStack) {
            if (aty.getClass().equals(cls)) {
                activity = aty;
                break;
            }
        }
        return (Activity) activity;
    }

    /**
     * 获取当前Activity（栈顶Activity）
     *
     * @return
     */
    public Activity topActivity() {
        if (mActivityStack == null) {
            throw new NullPointerException("Activity stack is null,your Activity must extents by LCActivity");
        }
        if (mActivityStack.isEmpty()) {
            return null;
        }
        I_ActivityCycle activity = mActivityStack.lastElement();
        return (Activity) activity;
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(LCActivity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            // activity.finish();//此处不用finish
            activity = null;
        }
    }
    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Class<?> cls) {
        for (I_ActivityCycle activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity((Activity) activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                ((Activity) mActivityStack.get(i)).finish();
            }
        }
        mActivityStack.clear();
    }
    /**
     * 应用程序退出
     *
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            Runtime.getRuntime().exit(0);
        } catch (Exception e) {
            Runtime.getRuntime().exit(-1);
        }
    }


}
