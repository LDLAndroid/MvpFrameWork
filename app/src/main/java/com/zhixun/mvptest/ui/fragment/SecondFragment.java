package com.zhixun.mvptest.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zhixun.mvptest.R;
import com.zhixun.mvptest.base.BaseFragment;
import com.zhixun.mvptest.component.AppComponent;
import com.zhixun.mvptest.view.psw.GridPasswordView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import yanzhikai.textpath.SyncTextPathView;

/**
 * Created by Administrator on 2018/6/25.
 */

public class SecondFragment extends BaseFragment {

    @BindView(R.id.clear)
    Button clear;
    @BindView(R.id.stpv_2017)
    SyncTextPathView stpv2017;
    @BindView(R.id.pswView)
    GridPasswordView pswView;
    Unbinder unbinder;

    public static SecondFragment newInstance() {
        Bundle args = new Bundle();
        SecondFragment fragment = new SecondFragment();
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
    @OnClick({R.id.tv_back, R.id.stpv_2017})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:

                break;
            case R.id.stpv_2017:
                break;
        }
    }
}
