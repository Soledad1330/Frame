package com.lvchuang.widget.media.video.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lvchuang.widget.R;
import com.lvchuang.widget.media.video.model.Video;
import com.lvchuang.widget.media.video.model.VideoUrl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by: Liu.ZhiYun on 2016/4/27.
 * Description:
 */
public class MediaController extends FrameLayout implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    private Context context;

    private ImageView mPlayImg;//播放按钮
    private SeekBar mProgressSeekBar;//播放进度条
    private TextView mTimeTxt;//播放时间
    private ImageView mExpandImg;//最大化播放按钮
    private ImageView mShrinkImg;//缩放播放按钮
    private EasySwitcher mVideoSrcSwitcher;//视频源切换器
    private EasySwitcher mVideoFormatSwitcher;//视频清晰度切换器
    private View mMenuView;
    private View mMenuViewPlaceHolder;

    private MediaControlImpl mMediaControl;

    public enum ProgressState {
        START, DOING, STOP
    }
    /**
     * 播放样式 展开、缩放
     */
    public enum PageType {
        EXPAND, SHRINK
    }
    /**
     * 播放状态 播放 暂停
     */
    public enum PlayState {
        PLAY, PAUSE
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean isFromUser) {
        if (isFromUser)
            mMediaControl.onProgressTurn(ProgressState.DOING, progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mMediaControl.onProgressTurn(ProgressState.START, 0);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mMediaControl.onProgressTurn(ProgressState.STOP, 0);
    }

    private EasySwitcher.EasySwitcherCallbackImpl mSrcSwitcherCallback = new EasySwitcher.EasySwitcherCallbackImpl() {
        @Override
        public void onSelectItem(int position, String name) {
            mMediaControl.onSelectSrc(position);
        }

        @Override
        public void onShowList() {
            mMediaControl.alwaysShowController();
            mVideoFormatSwitcher.closeSwitchList();
        }
    };

    private EasySwitcher.EasySwitcherCallbackImpl mFormatSwitcherCallback = new EasySwitcher.EasySwitcherCallbackImpl() {
        @Override
        public void onSelectItem(int position, String name) {
            mMediaControl.onSelectFormat(position);
        }

        @Override
        public void onShowList() {
            mMediaControl.alwaysShowController();
            mVideoSrcSwitcher.closeSwitchList();
        }
    };

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.controller_iv_pause) {
            mMediaControl.onPlayTurn();
        } else if (view.getId() == R.id.controller_iv_expand) {
            mMediaControl.onPageTurn();
        } else if (view.getId() == R.id.controller_iv_shrink) {
            mMediaControl.onPageTurn();
        }
    }

    public void initVideoList(ArrayList<Video> videoList) {
        ArrayList<String> name = new ArrayList<>();
        for (Video video : videoList) {
            name.add(video.getVideoName());
        }
        mVideoSrcSwitcher.initData(name);
    }

    public void initPlayVideo(Video video) {
        ArrayList<String> format = new ArrayList<>();
        for (VideoUrl url : video.getVideoUrl()) {
            format.add(url.getFormatName());
        }
        mVideoFormatSwitcher.initData(format);
    }

    public void closeAllSwitchList() {
        mVideoFormatSwitcher.closeSwitchList();
        mVideoSrcSwitcher.closeSwitchList();
    }

    /**
     * 初始化精简模式
     */
    public void initTrimmedMode() {
        mMenuView.setVisibility(GONE);
        mMenuViewPlaceHolder.setVisibility(GONE);
        mExpandImg.setVisibility(INVISIBLE);
        mShrinkImg.setVisibility(INVISIBLE);
    }

    /***
     * 强制横屏模式
     */
    public void forceLandscapeMode(){
        mExpandImg.setVisibility(INVISIBLE);
        mShrinkImg.setVisibility(INVISIBLE);
    }


    public void setProgressBar(int progress, int secondProgress) {
        if (progress < 0) progress = 0;
        if (progress > 100) progress = 100;
        if (secondProgress < 0) secondProgress = 0;
        if (secondProgress > 100) secondProgress = 100;
        mProgressSeekBar.setProgress(progress);
        mProgressSeekBar.setSecondaryProgress(secondProgress);
    }

    public void setPlayState(PlayState playState) {
        mPlayImg.setImageResource(playState.equals(PlayState.PLAY) ? R.drawable.ic_video_controller_pause : R.drawable.ic_video_controller_play);
    }

    public void setPageType(PageType pageType) {
        mExpandImg.setVisibility(pageType.equals(PageType.EXPAND) ? GONE : VISIBLE);
        mShrinkImg.setVisibility(pageType.equals(PageType.SHRINK) ? GONE : VISIBLE);
    }

    public void setPlayProgressTxt(int nowSecond, int allSecond) {
        mTimeTxt.setText(getPlayTime(nowSecond, allSecond));
    }

    public void playFinish(int allTime) {
        mProgressSeekBar.setProgress(0);
        setPlayProgressTxt(0, allTime);
        setPlayState(PlayState.PAUSE);
    }

    public void setMediaControl(MediaControlImpl mediaControl) {
        mMediaControl = mediaControl;
    }

    public MediaController(Context context) {
        super(context);
        initView();
    }
    

    public MediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MediaController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        this.context=getContext();
        View.inflate(context, R.layout.view_video_controller, this);
        mPlayImg = (ImageView) findViewById(R.id.controller_iv_pause);
        mProgressSeekBar = (SeekBar) findViewById(R.id.controller_sb_progress);
        mVideoSrcSwitcher = (EasySwitcher) findViewById(R.id.controller_view_src_switcher);
        mVideoFormatSwitcher = (EasySwitcher) findViewById(R.id.controller_view_format_switcher);
        mTimeTxt = (TextView) findViewById(R.id.controller_tv_time);
        mExpandImg = (ImageView) findViewById(R.id.controller_iv_expand);
        mShrinkImg = (ImageView) findViewById(R.id.controller_iv_shrink);
        mMenuView = findViewById(R.id.controller_ll_menu);
        mMenuViewPlaceHolder = findViewById(R.id.controller_view_menu_placeholder);
        initData();
    }

    private void initData() {
        mProgressSeekBar.setOnSeekBarChangeListener(this);
        mPlayImg.setOnClickListener(this);
        mShrinkImg.setOnClickListener(this);
        mExpandImg.setOnClickListener(this);
        setPageType(PageType.SHRINK);
        setPlayState(PlayState.PAUSE);
        mVideoFormatSwitcher.setEasySwitcherCallback(mFormatSwitcherCallback);
        mVideoSrcSwitcher.setEasySwitcherCallback(mSrcSwitcherCallback);
    }

    @SuppressLint("SimpleDateFormat")
    private String formatPlayTime(long time) {
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        return formatter.format(new Date(time));
    }

    private String getPlayTime(int playSecond, int allSecond) {
        String playSecondStr = "00:00";
        String allSecondStr = "00:00";
        if (playSecond > 0) {
            playSecondStr = formatPlayTime(playSecond);
        }
        if (allSecond > 0) {
            allSecondStr = formatPlayTime(allSecond);
        }
        return playSecondStr + "/" + allSecondStr;
    }

    public interface MediaControlImpl {
        void onPlayTurn();

        void onPageTurn();

        void onProgressTurn(ProgressState state, int progress);

        void onSelectSrc(int position);

        void onSelectFormat(int position);

        void alwaysShowController();
    }
}
