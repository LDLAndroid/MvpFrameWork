package com.zhixun.mvptest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhixun.mvptest.R;
import com.zhixun.mvptest.adapter.HomeAdapter;
import com.zhixun.mvptest.base.BaseActivity;
import com.zhixun.mvptest.base.BaseRVActivity;
import com.zhixun.mvptest.component.AppComponent;
import com.zhixun.mvptest.component.DaggerMainComponent;
import com.zhixun.mvptest.mvp.Contract.RefreshContract;
import com.zhixun.mvptest.mvp.presenter.FreshPresenter;
import com.zhixun.mvptest.ui.bean.HomeOrderData;
import com.zhixun.mvptest.view.MarqueeTextView;
import com.zhixun.mvptest.view.MarqueeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by Administrator on 2018/9/17.
 */

public class ReFreshActivity extends BaseRVActivity<FreshPresenter> implements RefreshContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.bga_banner)
    BGABanner bgaBanner;
    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.rl_msg)
    RelativeLayout rlMsg;
    @BindView(R.id.tv_tishi)
    MarqueeTextView tvTishi;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.ly_tishi)
    LinearLayout lyTishi;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_sort)
    TextView tvSort;
    @BindView(R.id.ll_sort)
    LinearLayout llSort;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;
    @BindView(R.id.ll_filter)
    LinearLayout llFilter;
    @BindView(R.id.ll_title)
    RelativeLayout llTitle;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private int applyDayStart = 0;//开始期限区间
    private int applyDayEnd = 100;//结束期限区间
    private int limit = 10;//每页限制显示多少条
    private int page = 1;//开始页码
    private Number yearRateStart = 0;
    private Number yearRateEnd = 100;
    private int sort = 0;
    private HomeAdapter homeAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_refreshs;
    }

    @Override
    protected void innitView() {
        requestApi();
    }

    private void requestApi() {
        mPresenter.queryOrderList(applyDayStart, applyDayEnd, limit, page, sort, yearRateStart, yearRateEnd);
    }


    @Override
    protected void innitData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        homeAdapter = new HomeAdapter(this);
        recyclerView.setAdapter(homeAdapter);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                       page=1;
                       requestApi();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                requestApi();
            }
        });
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    public static void startActivity(Context context) {
        Intent i = new Intent(context, ReFreshActivity.class);
        context.startActivity(i);
    }

    @Override
    public void showError(String err) {

    }

    @Override
    public void complete() {

    }

    @Override
    public void twoUser() {

    }

    @Override
    public void showqueryOrderList(HomeOrderData homeOrderData) {
        recyclerView.setVisibility(View.VISIBLE);
//        if (page == 1) {
//            refreshLayout.finishRefresh();
//            homeAdapter.refreshData(homeOrderData.getData());
//        }else{
//            refreshLayout.finishLoadMore();
//            homeAdapter.addData(homeOrderData.getData());
//        }


        if (homeOrderData != null) {
            if (homeOrderData.getData() == null || homeOrderData.getData().size() == 0) {
                //没有数据
                if (page == 1) {
                    recyclerView.setVisibility(View.GONE);
                    refreshLayout.setEnableLoadMore(true);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                refreshLayout.finishLoadMore(1000, true,false);
                if (page == 1) {
                    if (homeOrderData.getData().size() < 10) {
                        refreshLayout.setEnableLoadMore(false);
                    } else {
                        refreshLayout.setEnableLoadMore(true);
                    }
                    homeAdapter.refreshData(homeOrderData.getData());
                } else {
                    homeAdapter.addData(homeOrderData.getData());
                }
            }
        }
    }
}
