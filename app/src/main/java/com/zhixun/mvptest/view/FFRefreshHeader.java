package com.zhixun.mvptest.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.zhixun.mvptest.R;
/**
 * 自定义刷新头
 * Created by hook on 3/17/16.
 */
public class FFRefreshHeader extends LinearLayout implements RefreshHeader {

    private TextView tvRefresh;
    private ImageView ivRefreshIc;
    private Animation animation;

    @Override
    public void onPullingDown(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @Override
    public void onRefreshReleased(RefreshLayout layout, int headerHeight, int extendHeight) {

    }

    public enum HeaderTheme {
        THEME_WHITE,
        THEME_GRADIENT
    }

    public FFRefreshHeader(Context context) {
        super(context);
        initView();
    }

    public FFRefreshHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public FFRefreshHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.item_refresh_header, this);
        tvRefresh = (TextView) findViewById(R.id.tv_refresh_tips);
        ivRefreshIc = (ImageView) findViewById(R.id.iv_refresh_ic);
       // setTheme(HeaderTheme.THEME_WHITE);
        if (animation == null) {
            animation = new ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setRepeatMode(Animation.REVERSE);
            animation.setFillAfter(true);
            animation.setRepeatCount(Animation.INFINITE);
            animation.setDuration(100);
        }
        ivRefreshIc.setImageResource(R.mipmap.ic_refreshing);
    }


    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int extendHeight) {

    }


    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout layout, int height, int extendHeight) {

    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        ivRefreshIc.clearAnimation();
        ivRefreshIc.setImageResource(R.mipmap.ic_refreshed);
        if (success) {
            tvRefresh.setText(R.string.tips_refresh_success);
        } else {
            tvRefresh.setText(R.string.tips_refresh_fail);
        }
        return 500;
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                tvRefresh.setText(R.string.tips_pull_refresh);
                ivRefreshIc.setImageResource(R.mipmap.ic_refreshing);
                ivRefreshIc.startAnimation(animation);
                break;
            case Refreshing:
                tvRefresh.setText(R.string.tips_refreshing);
                break;
            case ReleaseToRefresh:
                tvRefresh.setText(R.string.tips_loosen_refresh);
                break;
        }
    }

    public void setTheme(HeaderTheme theme) {
        switch (theme) {
            case THEME_WHITE:
               // setTheme(R.color.colorPrimary, R.color.colorTextGray);
                break;
            case THEME_GRADIENT:
               // setTheme(R.drawable.rectangle_gradient_bg, R.color.colorPrimary);
                break;
            default:
                break;
        }
    }

    @SuppressWarnings("deprecation")
    public void setTheme(int background, int textColor) {
        setBackgroundResource(background);
        tvRefresh.setTextColor(getContext().getResources().getColor(textColor));
    }

}