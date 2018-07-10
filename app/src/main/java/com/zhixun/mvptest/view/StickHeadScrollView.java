package com.zhixun.mvptest.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import com.zhixun.mvptest.utils.CommonUtils;


/**
 * 优化嵌套滑动(可保留置顶控件)
 * Created by panchenhuan on 17/1/4..
 */

public class StickHeadScrollView extends NestedScrollView {
    private OnScrollListener listener;

    public void setOnScrollListener(OnScrollListener listener) {
        this.listener = listener;
    }
    //设置接口
    public interface OnScrollListener{
        void onScroll(int scrollY);
    }

    public StickHeadScrollView(Context context) {
        super(context);
    }

    public StickHeadScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StickHeadScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
//                                      llTitle,            recyclerView
    public void resetHeight(final View headView, final View nestedContent) {
        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                StickHeadScrollView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                nestedContent.getLayoutParams().height = StickHeadScrollView.this.getHeight() - headView.getHeight()- CommonUtils.getStatusBarHeight(getContext());
                nestedContent.requestLayout();
            }
        });
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        //判断是否需要优先嵌套滑动,解决触摸recyclerview 无法关闭swiperefreshlayout的bug
        if (!dispatchNestedPreScroll(dx, dy, consumed, null)) {
            //嵌套滑动代码
            boolean hiddenTop = dy > 0 && getScrollY() < getChildAt(0).getHeight() - getHeight();
            boolean showTop = dy < 0 && getScrollY() > 0 && !ViewCompat.canScrollVertically(target, -1);

            if (hiddenTop || showTop) {
                scrollBy(0, dy);
                consumed[1] = dy;
            }
        }
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if (getScrollY() >= getChildAt(0).getHeight() - getHeight())
            return false;
        fling((int) velocityY);
        return false;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(listener != null){
            //这里我只传了垂直滑动的距离
            listener.onScroll(t);
        }
    }
}
