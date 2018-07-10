package com.zhixun.mvptest.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.zhixun.mvptest.App;
import com.zhixun.mvptest.component.AppComponent;
import com.zhixun.mvptest.dialog.LoadingDialog;
import com.zhixun.mvptest.view.ZToast;

import java.util.regex.Pattern;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/15.
 */

public abstract class BaseFragment extends Fragment {

    protected View parentView;
    protected FragmentActivity activity;
    protected LayoutInflater inflater;
    protected Context mContext;


    protected LoadingDialog juhuaDialog;

    // 控件是否初始化完成
    // 我们在控件初始化完成之后再进行数据的加载，否则对控件进行操作的时候会遇到空指针异常
    protected boolean isViewCreated;

    // 是否加载过数据
    // 我们判断未曾加载过数据的话再进行获取，否则每次对用户可见时都会执行懒加载的方法
    protected boolean isLoadCompleted;

    // 该方法只有在ViewPager与Fragment结合使用的时候才会执行
    // 该方法在onCreateView之前调用
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated && !isLoadCompleted) {
            // 只有在对用户可见、控件初始化完成并且未曾加载过数据的情况下才进行懒加载
            lazyLoad();
            isLoadCompleted = true;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        parentView = inflater.inflate(getLayoutResId(), container, false);
        activity = getSupportActivity();
        mContext = activity;
        this.inflater = inflater;

        /** 预防 点击击穿，实现下面的fragment的点击事件*/
        parentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        return parentView;
    }
    // ViewPager的第一个Fragment默认执行setUserVisibleHint(fasle)方法
    // 所以在activity创建完成后要让第一页也加载数据
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            // 此处不需要判断isViewCreated，因为这个方法在onCreateView方法之后执行
            lazyLoad();
            isLoadCompleted = true;
        }
    }

    // 懒加载,强制子类重写  需要使用懒加载时在这个方法内部处理网络请求
    protected abstract void lazyLoad();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupActivityComponent(App.getInstance().getAppComponent());
        attachView();
        configViews();
        initDatas();
    }


    public abstract void attachView();

    @LayoutRes
    public abstract int getLayoutResId();

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    public abstract void configViews();

    public abstract void initDatas();


    protected abstract void setupActivityComponent(AppComponent appComponent);

    public FragmentActivity getSupportActivity() {
        return (FragmentActivity) super.getActivity();
    }

    public Context getApplicationContext() {
        return this.activity == null ? (getActivity() == null ? null : getActivity()
                .getApplicationContext()) : this.activity.getApplicationContext();
    }


    /**
     * 显示dialog
     *
     * @param dialogText 可为空，为空不显示文字
     */
    protected void showDialog(String dialogText) {
        juhuaDialog = new LoadingDialog(getActivity(), dialogText);
        juhuaDialog.show();
    }

    /**
     * 隐藏dialog
     */
    protected void dismissDialog() {
        if (juhuaDialog != null)
            juhuaDialog.dismiss();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }


    public boolean matchPhone(String text) {
        if (Pattern.compile("(\\d{11})|(\\+\\d{3,})").matcher(text).matches()) {
            return true;
        }
        return false;
    }
}
