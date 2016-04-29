package com.lvchuang.widget.actionbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lvchuang.widget.R;

/**
 * Created by: Liu.ZhiYun on 2016/4/26.
 * Description:
 */
public class TopBar extends RelativeLayout {
    private RelativeLayout topbar_rl_left;
    private RelativeLayout topbar_rl_right;
    private ImageView topbar_iv_left;
    private ImageView topbar_iv_right;
    private TextView topbar_tv_left;
    private TextView topbar_tv_title;
    private TextView topbar_tv_right;

    private String leftMenuStr;
    private String titleStr;
    private String rightMenuStr;

    private Bitmap leftIconBitmap;
    private Bitmap rightIconBitmap;

    private OnTopbarClickedCallback listener;

    public TopBar(Context context) {
        super(context);
        initView();
    }
    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_topbar, null);
        topbar_rl_left = (RelativeLayout) view.findViewById(R.id.topbar_rl_left);
        topbar_rl_right = (RelativeLayout) view.findViewById(R.id.topbar_rl_right);
        topbar_iv_left = (ImageView) view.findViewById(R.id.topbar_iv_left);
        topbar_iv_right = (ImageView) view.findViewById(R.id.topbar_iv_right);
        topbar_tv_left = (TextView) view.findViewById(R.id.topbar_tv_left);
        topbar_tv_title = (TextView) view.findViewById(R.id.topbar_tv_title);
        topbar_tv_right = (TextView) view.findViewById(R.id.topbar_tv_right);

        topbar_rl_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onLeftCallback();
                }
            }
        });
        topbar_rl_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRightCallback();
                }
            }
        });
        addView(view);
    }

    public void addClickListener(OnTopbarClickedCallback listener) {
        this.listener = listener;
    }

    public void setTextRes(int leftStrRes, int titleStrRes, int rightStrRes) {
        try {
            leftMenuStr = getContext().getResources().getString(leftStrRes);
        } catch (Exception e) {
            leftMenuStr = "";
            throw new RuntimeException("String resourceId can`t find!");

        }
        try {
            titleStr = getContext().getResources().getString(titleStrRes);
        } catch (Exception e) {
            titleStr = "";
            throw new RuntimeException("String resourceId can`t find!");
        }
        try {
            rightMenuStr = getContext().getResources().getString(rightStrRes);
        } catch (Exception e) {
            rightMenuStr = "";
            throw new RuntimeException("String resourceId can`t find!");
        }
        topbar_tv_left.setText(leftMenuStr);
        topbar_tv_title.setText(titleStr);
        topbar_tv_right.setText(rightMenuStr);
    }

    public void setImageRes(int leftImgrRes, int rightImgrRes) {
        try {
            leftIconBitmap = BitmapFactory.decodeResource(getContext().getResources(), leftImgrRes);
        } catch (Exception e) {
            throw new RuntimeException("Bitmap resourceId can`t find!");
        }
        try {
            rightIconBitmap = BitmapFactory.decodeResource(getContext().getResources(), rightImgrRes);
        } catch (Exception e) {
            throw new RuntimeException("Bitmap resourceId can`t find!");
        }
        if (leftIconBitmap != null) {
            topbar_iv_left.setImageBitmap(leftIconBitmap);
        }
        if (rightIconBitmap != null) {
            topbar_iv_right.setImageBitmap(rightIconBitmap);
        }
    }

    public interface OnTopbarClickedCallback {
        void onLeftCallback();

        void onRightCallback();
    }
}
