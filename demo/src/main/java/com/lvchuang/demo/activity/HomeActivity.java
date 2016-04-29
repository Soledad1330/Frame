package com.lvchuang.demo.activity;

import android.content.DialogInterface;
import android.os.Bundle;

import com.lvchuang.demo.R;
import com.lvchuang.library.LCActivity;
import com.lvchuang.library.ui.base.LCActivityManager;
import com.lvchuang.library.ui.port.BindView;
import com.lvchuang.library.utils.DialogUtil;
import com.lvchuang.widget.actionbar.TopBar;

/**
 * Created by: Liu.ZhiYun on 2016/4/27.
 * Description:
 */
public class HomeActivity extends LCActivity {

    @BindView(id = R.id.home_view_topbar)
    private TopBar home_view_topbar;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_home);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        home_view_topbar.setTextRes(R.string.home_menu_left, R.string.home_title, R.string.home_menu_right);
        home_view_topbar.setImageRes(R.drawable.ico_home_left_menu, R.drawable.ico_home_right_menu);
        home_view_topbar.addClickListener(new TopBar.OnTopbarClickedCallback() {
            @Override
            public void onLeftCallback() {
                DialogUtil.toast("Left Menu!");
                showLeftMenu();

            }

            @Override
            public void onRightCallback() {
                DialogUtil.toast("Right Menu!");
                showRightMenu();
            }
        });
    }

    private void showRightMenu() {
        Bundle bundle=new Bundle();
        bundle.putString("video_url","http://lom.zqgame.com/v1/video/LOM_Promo~2.flv");
        showActivity(HomeActivity.this, VideoPlayActivity.class,bundle);
    }

    private void showLeftMenu() {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DialogUtil.create().getExitDialog(mContext, "Are you sure exit App?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LCActivityManager.create().AppExit(mContext);
            }
        });
    }
}
