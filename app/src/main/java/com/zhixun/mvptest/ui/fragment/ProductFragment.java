package com.zhixun.mvptest.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.zhixun.mvptest.R;
import com.zhixun.mvptest.adapter.BaseFragmentPagerAdapter;
import com.zhixun.mvptest.base.BaseFragment;
import com.zhixun.mvptest.component.AppComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/6/25.
 */

public class ProductFragment extends BaseFragment {

    @BindView(R.id.qts)
    QMUITabSegment qts;
    @BindView(R.id.vp)
    ViewPager vp;
    private List<Fragment> fragmentList = new ArrayList<>();

    public static ProductFragment newInstance() {
        Bundle args = new Bundle();
        ProductFragment fragment = new ProductFragment();
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
        return R.layout.fragment_product;
    }

    @Override
    public void configViews() {
        fragmentList.add(FirstFragment.newInstance());
        fragmentList.add(SecondFragment.newInstance());
        BaseFragmentPagerAdapter baseFragmentPagerAdapter=new BaseFragmentPagerAdapter(getFragmentManager(),fragmentList);
        vp.setAdapter(baseFragmentPagerAdapter);
        qts.addTab(new QMUITabSegment.Tab("公告"));
        qts.addTab(new QMUITabSegment.Tab("消息"));
        qts.setHasIndicator(true);
        qts.setMode(QMUITabSegment.MODE_FIXED);
        qts.setDefaultNormalColor(mContext.getResources().getColor(R.color.black));
        qts.setDefaultSelectedColor(mContext.getResources().getColor(R.color.c_f95e49));
        QMUITabSegment.Tab qtsTab = qts.getTab(0);
        qtsTab.setSignCountMargin(0, -QMUIDisplayHelper.dp2px(mContext, 4));//设置红点显示位置
        qtsTab.setmSignCountDigits(5);//设置红点中数字显示的最大位数
        qtsTab.showSignCountView(mContext,8);//第二个参数表示：显示的消息数
        QMUITabSegment.Tab qtsTab1 = qts.getTab(1);
        qtsTab1.setSignCountMargin(0, -QMUIDisplayHelper.dp2px(mContext, 4));//设置红点显示位置
        qtsTab1.setmSignCountDigits(5);//设置红点中数字显示的最大位数
        qtsTab1.showSignCountView(mContext,3);//第二个参数表示：显示的消息数
        qts.setupWithViewPager(vp,false);
    }
    @Override
    public void initDatas() {
    }
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
    }

}
