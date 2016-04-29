package com.lvchuang.widget.media.video.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lvchuang.widget.R;
import com.lvchuang.widget.media.video.util.DensityUtil;

import java.util.ArrayList;

/**
 * Created by: Liu.ZhiYun on 2016/4/27.
 * Description:
 */
public class EasySwitcher extends LinearLayout implements View.OnClickListener {
    private Context context;

    private static final int SWITCHER_TV_ID=0x1021;
    private final int NORMAL_ICON = R.drawable.ic_video_switcher_prior;
    private final int SELECT_ICON = R.drawable.ic_video_switcher_next;

    private LinearLayout switcher_ll_item_container;
    private TextView switcher_tv_tab;
    private ArrayList<String> mAllItemArray;
    private int mDefaultSelection;

    private EasySwitcherCallbackImpl mEasySwitcherCallback;


    public EasySwitcher(Context context) {
        super(context);
        mAllItemArray = new ArrayList<>();
        mDefaultSelection = 0;
        initBaseView();
    }

    public EasySwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        mAllItemArray = new ArrayList<>();
        mDefaultSelection = 0;
        initBaseView();
    }

    public EasySwitcher(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mAllItemArray = new ArrayList<>();
        mDefaultSelection = 0;
        initBaseView();
    }

    private void initBaseView() {
        this.context=getContext();
        View.inflate(context, R.layout.view_video_switcher, this);
        switcher_ll_item_container = (LinearLayout)findViewById(R.id.switcher_ll_item_container);
        switcher_tv_tab = (TextView)findViewById(R.id.switcher_tv_tab);
        setSelected(false);
        switcher_tv_tab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.switcher_tv_tab){
            if(closeSwitchList()){
                return;
            }
            mEasySwitcherCallback.onShowList();
            showItemList();
        }
    }
    /***
     * 关闭弹出的选择列表
     * @return 是否消耗了执行事件。 如果没有，就代表没有列表不需要关闭
     */
    public boolean closeSwitchList(){
        if(switcher_ll_item_container.getChildCount() > 0){
            switcher_ll_item_container.removeAllViews();
            setSelectItemStyle(false);
            return true;
        }
        return false;
    }

    private void showItemList(){
        setSelectItemStyle(true);
        switcher_ll_item_container.removeAllViews();
        for(int i = 0;i < mAllItemArray.size();i++){
            switcher_ll_item_container.addView(getItemView(i));
            switcher_ll_item_container.addView(getDividerView());
        }
    }


    private void setSelectItemStyle(boolean isSelect){
        switcher_tv_tab.setSelected(isSelect);
        Drawable rightDrawable = context.getResources().getDrawable(isSelect?SELECT_ICON:NORMAL_ICON);
        if (null != rightDrawable)
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        switcher_tv_tab.setCompoundDrawables(null, null, rightDrawable, null);
    }

    public void setEasySwitcherCallback(EasySwitcherCallbackImpl easySwitcherCallback) {
        mEasySwitcherCallback = easySwitcherCallback;
    }

    public interface EasySwitcherCallbackImpl {
        void onSelectItem(int position, String name);

        void onShowList();
    }
    private View getItemView(int position){
        TextView textView = new TextView(context);
        textView.setTextAppearance(context, R.style.switcher_item_text_style);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                DensityUtil.dip2px(context,35));
        textView.setLayoutParams(layoutParams);
        textView.setSelected(position == mDefaultSelection);
        textView.setGravity(Gravity.CENTER);
        textView.setText(mAllItemArray.get(position));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12.0f);
        textView.setOnClickListener(mItemOnClickListener);
        textView.setTag(position);
        return textView;
    }
    private OnClickListener mItemOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(null != v && v instanceof TextView){
                int position = (int)v.getTag();
                String name = ((TextView)v).getText().toString();
                closeSwitchList();
                switcher_tv_tab.setText(name);
                setSelectItemStyle(false);
                mDefaultSelection = position;
                mEasySwitcherCallback.onSelectItem(position,name);
            }
        }
    };
    private View getDividerView(){
        View view = new View(context);
        view.setBackgroundColor(context.getResources().getColor(R.color.divider_color));
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,3));
        return view;
    }


    public void initData(ArrayList<String> allItemArray){
        mAllItemArray.clear();
        mAllItemArray.addAll(allItemArray);
        updateSelectItem(0);
    }
    public void updateSelectItem(int selectPosition){
        if(null == mAllItemArray || selectPosition < 0 || selectPosition >= mAllItemArray.size())return;
        mDefaultSelection = selectPosition;
        switcher_tv_tab.setText(mAllItemArray.get(selectPosition));
        switcher_ll_item_container.removeAllViews();
        switcher_tv_tab.setSelected(false);
    }

}
