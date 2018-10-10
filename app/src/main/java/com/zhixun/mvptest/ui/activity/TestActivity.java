package com.zhixun.mvptest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zhixun.mvptest.R;
import com.zhixun.mvptest.adapter.QuickAdapter;
import com.zhixun.mvptest.base.BaseActivity;
import com.zhixun.mvptest.component.AppComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/14.
 */

public class TestActivity extends BaseActivity {
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.tv_nonet)
    TextView tvNonet;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    private List<String> dataList = new ArrayList<>();
    private QuickAdapter quickAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity;
    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void innitView() {
        rlv.setVisibility(View.VISIBLE);
        tvNonet.setVisibility(View.GONE);
        for (int i = 0; i < 35; i++) {
            dataList.add("万能recyclerview条目就大部分时间金风科技东方今典京东方积分继父回家发挥第三方很舒服还打算放松放松" + i);
        }
        quickAdapter = new QuickAdapter(R.layout.item_layout, dataList);
        quickAdapter.setEnableLoadMore(true);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        rlv.setAdapter(quickAdapter);
        quickAdapter.openLoadAnimation(QuickAdapter.SCALEIN);
    }

    @Override
    protected void innitData() {

        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                System.out.println("*************verticalOffset**************" + verticalOffset);

            }
        });
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    public static void startActivity(Context context) {
        Intent i = new Intent(context, TestActivity.class);
        context.startActivity(i);
    }


    @OnClick({R.id.tvtv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvtv:
                appBar.setExpanded(false, true);
                break;
        }
    }
}
