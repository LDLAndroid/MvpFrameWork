package com.zhixun.mvptest.ui.fragment;

import android.os.Bundle;

import com.zhixun.mvptest.R;
import com.zhixun.mvptest.base.BaseFragment;
import com.zhixun.mvptest.component.AppComponent;

/**
 * Created by Administrator on 2018/6/25.
 */

public class AttentionFragment extends BaseFragment {

    public static AttentionFragment newInstance() {
        Bundle args = new Bundle();
        AttentionFragment fragment = new AttentionFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void lazyLoad() {

    }

    @Override
    public void attachView() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_attention;
    }

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }
}
