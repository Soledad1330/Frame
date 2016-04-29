package com.lvchuang.library.ui.port;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by: Liu.ZhiYun on 2016/4/22.
 * Description:
 */
public interface I_SkipActivity {
    /**
     * 跳转clazz页并关闭本Activity
     */
    public void skipActivity(Activity aty, Intent intent);

    /**
     * 跳转clazz页并关闭本Activity
     */

    public void skipActivity(Activity aty, Class<?> clazz);

    /**
     * 跳转clazz页并关闭本Activity
     */
    public void skipActivity(Activity aty, Class<?> clazz, Bundle bundle);

    /**
     * 跳转clazz页,但不finish本Activity
     */
    public void showActivity(Activity aty, Intent intent);

    /**
     * 跳转clazz页,但不finish本Activity
     */
    public void showActivity(Activity aty, Class<?> clazz);

    /**
     * 跳转clazz页,但不finish本Activity
     */
    public void showActivity(Activity aty, Class<?> clazz, Bundle bundle);


}
