package com.lvchuang.widget.guide.appintro;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lvchuang.widget.R;
/**
 * Created by: Liu.ZhiYun on 2016/4/27.
 * Description:引导页(Model)基类
 */
public abstract class AppIntro extends AppIntroBase {
    private static final String TAG = "AppIntro";

    protected boolean skipButtonEnabled = true;

    protected View skipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        skipButton = findViewById(R.id.skip);

        skipButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(@NonNull View v)
            {
                if (isVibrateOn)
                {
                    mVibrator.vibrate(vibrateIntensity);
                }
                onSkipPressed(mPagerAdapter.getItem(pager.getCurrentItem()));
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_intro_layout;
    }

    @Override
    protected void onPageSelected(int position)
    {
        super.onPageSelected(position);
        setButtonState(skipButton, skipButtonEnabled);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("skipButtonEnabled", skipButtonEnabled);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);


        this.skipButtonEnabled = savedInstanceState.getBoolean("skipButtonEnabled");
    }

    public boolean isSkipButtonEnabled() {
        return skipButtonEnabled;
    }

    /**
     * Called when the user clicked the skip button
     * @deprecated Override {@link #onSkipPressed(Fragment)} instead
     */
    public void onSkipPressed() {

    }

    /**
     * Called when the user clicked the skip button
     * @param currentFragment Instance of the currently displayed fragment
     */
    public void onSkipPressed(Fragment currentFragment) {
        onSkipPressed();
    }

    /**
     * Override viewpager bar color
     *
     * @param color your color resource
     */
    public void setBarColor(@ColorInt final int color) {
        LinearLayout bottomBar = (LinearLayout) findViewById(R.id.bottom);
        bottomBar.setBackgroundColor(color);
    }

    /**
     * Override next button arrow color
     *
     * @param color your color
     *
     */
    public void setNextArrowColor(@ColorInt final int color) {
        ImageButton nextButton = (ImageButton) findViewById(R.id.next);
        nextButton.setColorFilter(color);
    }

    /**
     * Override separator color
     *
     * @param color your color resource
     */
    public void setSeparatorColor(@ColorInt final int color) {
        TextView separator = (TextView) findViewById(R.id.bottom_separator);
        separator.setBackgroundColor(color);
    }

    /**
     * Override skip text
     *
     * @param text your text
     */
    public void setSkipText(@Nullable final CharSequence text) {
        TextView skipText = (TextView) findViewById(R.id.skip);
        skipText.setText(text);
    }

    /**
     * Override done text
     *
     * @param text your text
     */
    public void setDoneText(@Nullable final CharSequence text) {
        TextView doneText = (TextView) findViewById(R.id.done);
        doneText.setText(text);
    }

    /**
     * Override done button text color
     *
     * @param colorDoneText your color resource
     */
    public void setColorDoneText(@ColorInt final int colorDoneText) {
        TextView doneText = (TextView) findViewById(R.id.done);
        doneText.setTextColor(colorDoneText);
    }

    /**
     * Override skip button color
     *
     * @param colorSkipButton your color resource
     */
    public void setColorSkipButton(@ColorInt final int colorSkipButton) {
        TextView skip = (TextView) findViewById(R.id.skip);
        skip.setTextColor(colorSkipButton);
    }

    /**
     * Override Next button
     *
     * @param imageNextButton your drawable resource
     */
    public void setImageNextButton(@DrawableRes final Drawable imageNextButton) {
        final ImageView nextButton = (ImageView) findViewById(R.id.next);
        nextButton.setImageDrawable(imageNextButton);

    }

    /**
     * Setting to to display or hide the Skip button. This is a static setting and
     * button state is maintained across slides until explicitly changed.
     *
     * @param showButton Set true to display. False to hide.
     */
    public void showSkipButton(boolean showButton) {
        this.skipButtonEnabled = showButton;
        setButtonState(skipButton, showButton);
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
}
