package com.lvchuang.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.lvchuang.demo.R;
import com.lvchuang.demo.frag.InputDemoSlide;
import com.lvchuang.demo.frag.SampleSlide;
import com.lvchuang.widget.guide.appintro.AppIntro;

/**
 * Created by: Liu.ZhiYun on 2016/4/27.
 * Description:引导页(App首次运行)
 */
public class GuideActivity extends AppIntro {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(SampleSlide.newInstance(R.layout.frag_intro1));
        addSlide(SampleSlide.newInstance(R.layout.frag_intro2));
        addSlide(SampleSlide.newInstance(R.layout.frag_intro3));
        addSlide(SampleSlide.newInstance(R.layout.frag_intro4));
        addSlide(new InputDemoSlide());
    }

    private void loadLoginActivity() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        GuideActivity.this.finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        loadLoginActivity();
        Toast.makeText(getApplicationContext(), getString(R.string.guide_top_skip), Toast.LENGTH_SHORT).show();
    }


    public void getStarted(View v) {
        loadLoginActivity();
    }
}
