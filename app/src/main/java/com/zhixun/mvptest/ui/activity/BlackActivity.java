package com.zhixun.mvptest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zhixun.mvptest.R;
import com.zhixun.mvptest.base.BaseActivity;
import com.zhixun.mvptest.component.AppComponent;
import com.zhixun.mvptest.view.LolView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/21.
 */

public class BlackActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_black;
    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void innitView() {
    }

    @Override
    protected void innitData() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    public static void startActivity(Context context) {
        Intent i = new Intent(context, BlackActivity.class);
        context.startActivity(i);
    }

}
