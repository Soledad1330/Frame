package com.lvchuang.demo.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lvchuang.demo.R;
import com.lvchuang.library.LCActivity;
import com.lvchuang.library.ui.port.BindView;
import com.lvchuang.widget.media.video.dlna.engine.DLNAContainer;
import com.lvchuang.widget.media.video.dlna.service.DLNAService;
import com.lvchuang.widget.media.video.model.Video;
import com.lvchuang.widget.media.video.model.VideoUrl;
import com.lvchuang.widget.media.video.util.DensityUtil;
import com.lvchuang.widget.media.video.view.MediaController;
import com.lvchuang.widget.media.video.view.SuperVideoPlayer;

import java.util.ArrayList;

/**
 * Created by: Liu.ZhiYun on 2016/4/27.
 * Description:
 */
public class VideoPlayActivity extends LCActivity {
    private String mVideoUrl;

    @BindView(id = R.id.video_view_super_videoview)
    private SuperVideoPlayer video_view_super_videoview;

    @BindView(id = R.id.video_iv_play)
    private ImageView video_iv_play;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_video_play);
    }

    @Override
    public void initData() {
        super.initData();
        mVideoUrl = getIntent().getStringExtra("video_url");
    }

    @Override
    public void initWidget() {
        super.initWidget();
        video_iv_play.setOnClickListener(this);
        video_view_super_videoview.setVideoPlayCallback(mVideoPlayCallback);
        startDLNAService();
    }

    private SuperVideoPlayer.VideoPlayCallbackImpl mVideoPlayCallback = new SuperVideoPlayer.VideoPlayCallbackImpl() {
        @Override
        public void onCloseVideo() {
            video_view_super_videoview.close();
            video_view_super_videoview.setVisibility(View.VISIBLE);
            video_view_super_videoview.setVisibility(View.GONE);
            resetPageToPortrait();
        }

        @Override
        public void onSwitchPageType() {
            if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                video_view_super_videoview.setPageType(MediaController.PageType.SHRINK);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                video_view_super_videoview.setPageType(MediaController.PageType.EXPAND);
            }
        }

        @Override
        public void onPlayFinish() {

        }
    };

    @Override
    public void onClick(View view) {
        video_view_super_videoview.setVisibility(View.GONE);
        video_view_super_videoview.setVisibility(View.VISIBLE);
        video_view_super_videoview.setAutoHideController(false);

        Video video = new Video();
        VideoUrl videoUrl1 = new VideoUrl();
        videoUrl1.setFormatName("720P");
        videoUrl1.setFormatUrl(mVideoUrl);
        VideoUrl videoUrl2 = new VideoUrl();
        videoUrl2.setFormatName("480P");
        videoUrl2.setFormatUrl(mVideoUrl);
        ArrayList<VideoUrl> arrayList1 = new ArrayList<>();
        arrayList1.add(videoUrl1);
        arrayList1.add(videoUrl2);
        video.setVideoName("测试视频一");
        video.setVideoUrl(arrayList1);
        ArrayList<Video> videoArrayList = new ArrayList<>();
        videoArrayList.add(video);
        video_view_super_videoview.loadMultipleVideo(videoArrayList, 0, 0, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopDLNAService();
    }

    /***
     * 旋转屏幕之后回调
     *
     * @param newConfig newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (null == video_view_super_videoview) return;
        /***
         * 根据屏幕方向重新设置播放器的大小
         */
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().getDecorView().invalidate();
            float height = DensityUtil.getWidthInPx(this);
            float width = DensityUtil.getHeightInPx(this);
            video_view_super_videoview.getLayoutParams().height = (int) width;
            video_view_super_videoview.getLayoutParams().width = (int) height;
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            final WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            float width = DensityUtil.getWidthInPx(this);
            float height = DensityUtil.dip2px(this, 200.f);
            video_view_super_videoview.getLayoutParams().height = (int) height;
            video_view_super_videoview.getLayoutParams().width = (int) width;
        }
    }

    /***
     * 恢复屏幕至竖屏
     */
    private void resetPageToPortrait() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            video_view_super_videoview.setPageType(MediaController.PageType.SHRINK);
        }
    }

    private void startDLNAService() {
        // Clear the device container.
        DLNAContainer.getInstance().clear();
        Intent intent = new Intent(getApplicationContext(), DLNAService.class);
        startService(intent);
    }

    private void stopDLNAService() {
        Intent intent = new Intent(getApplicationContext(), DLNAService.class);
        stopService(intent);
    }

}
