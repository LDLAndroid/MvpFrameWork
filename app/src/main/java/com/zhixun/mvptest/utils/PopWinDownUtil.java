package com.zhixun.mvptest.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.zhixun.mvptest.R;


/**
 * Created by cuijunling on 2016/11/1.
 */
public class PopWinDownUtil {
    private Context context;
    private View contentView;
    private View relayView;
    private PopupWindow popupWindow;

    public PopWinDownUtil(Context context, View contentView, View relayView) {
        this.context = context;
        this.contentView = contentView;
        this.relayView = relayView;
        init();
    }

    @SuppressLint("WrongConstant")
    public void init() {
        //内容，高度，宽度
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //动画效果
        popupWindow.setAnimationStyle(R.style.AnimationTopFade);
        //菜单背景色
        ColorDrawable dw = new ColorDrawable(Color.parseColor("#77000000"));
        popupWindow.setBackgroundDrawable(dw);

        popupWindow.setFocusable(true);//这里必须设置为true才能点击区域外或者消失

        popupWindow.setTouchable(true);//这个控制PopupWindow内部控件的点击事件


        popupWindow.setOutsideTouchable(false);

        popupWindow.update();
        //关闭事件
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (onDismissLisener != null) {
                    onDismissLisener.onDismiss();
                }
            }
        });
        if (contentView != null) {
            contentView.setFocusable(true);
            View view = contentView.findViewById(R.id.view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                }
            });
        }

    }

    public void show() {
        if (popupWindow != null) {
            popupWindow.setTouchable(true);
        }

        //显示位置
        if (!popupWindow.isShowing()) {
            if (Build.VERSION.SDK_INT!=24) {
                //只有24这个版本有问题，好像是源码的问题
                popupWindow.showAsDropDown(relayView);
            } else {
                //7.0  得这么写
                int[] location = new int[2];
                relayView.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                popupWindow.showAtLocation(relayView, Gravity.NO_GRAVITY, 0, y+relayView.getHeight());
            }
        }

    }

    public void hide() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    private OnDismissLisener onDismissLisener;

    public void setOnDismissListener(OnDismissLisener onDismissLisener) {
        this.onDismissLisener = onDismissLisener;
    }

    public interface OnDismissLisener {
        void onDismiss();
    }

    public boolean isShowing() {
        return popupWindow.isShowing();
    }
}
