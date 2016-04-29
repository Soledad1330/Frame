package com.lvchuang.widget.guide.appintro;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.view.View;
import android.widget.FrameLayout;

import com.lvchuang.widget.R;

import java.util.ArrayList;

/**
 * Created by: Liu.ZhiYun on 2016/4/27.
 * Description:
 */
public abstract class AppIntro2 extends AppIntroBase {
    private static final String TAG = "AppIntro2";

    private boolean STATUS_BAR_VISIBLE = false;

    protected View customBackgroundView;
    protected FrameLayout backgroundFrame;
    private ArrayList<Integer> transitionColors;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backgroundFrame = (FrameLayout) findViewById(R.id.background);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_intro_layout2;
    }

    /**
     * Shows or hides Done button, replaced with setProgressButtonEnabled
     *
     * @deprecated use {@link #setProgressButtonEnabled(boolean)} instead.
     */
    @Deprecated
    public void showDoneButton(boolean showDone) {
        setProgressButtonEnabled(showDone);
    }

    public void setBackgroundView(View view){
        customBackgroundView = view;
        if (customBackgroundView!=null){
            backgroundFrame.addView(customBackgroundView);
        }
    }

    /**
     * For color transition, will be shown only if color values are properly set and
     * Size of the color array must be equal to the number of slides added
     * @param colors Set color values
     * */
    public void setAnimationColors(@ColorInt ArrayList<Integer> colors) {
        transitionColors = colors;
    }
}
