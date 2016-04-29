
package com.lvchuang.widget.guide.appintro;

import android.os.Bundle;

import com.lvchuang.widget.R;

/**
 * Created by: Liu.ZhiYun on 2016/4/27.
 * Description:
 */
public final class AppIntro2Fragment extends AppIntroBaseFragment {
    public static AppIntroFragment newInstance(CharSequence title, CharSequence description, int imageDrawable, int bgColor) {
        return newInstance(title, description, imageDrawable, bgColor, 0, 0);
    }

    public static AppIntroFragment newInstance(CharSequence title, CharSequence description, int imageDrawable, int bgColor, int titleColor, int descColor) {
        AppIntroFragment slide = new AppIntroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title.toString());
        args.putString(ARG_DESC, description.toString());
        args.putInt(ARG_DRAWABLE, imageDrawable);
        args.putInt(ARG_BG_COLOR, bgColor);
        args.putInt(ARG_TITLE_COLOR, titleColor);
        args.putInt(ARG_DESC_COLOR, descColor);
        slide.setArguments(args);
        return slide;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_intro2;
    }
}
