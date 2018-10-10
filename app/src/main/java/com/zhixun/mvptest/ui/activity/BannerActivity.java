package com.zhixun.mvptest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.kelin.banner.BannerEntry;
import com.kelin.banner.view.BannerView;
import com.kelin.banner.view.PointIndicatorView;
import com.zhixun.mvptest.R;
import com.zhixun.mvptest.base.BaseActivity;
import com.zhixun.mvptest.component.AppComponent;
import com.zhixun.mvptest.utils.ToastTextUtils;
import com.zhixun.mvptest.view.CardPageTransformer;
import com.zhixun.mvptest.view.ImageBannerEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/10.
 */

public class BannerActivity extends BaseActivity {
    @BindView(R.id.vp_view_pager)
    BannerView vpViewPager;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_sub_title)
    TextView tvSubTitle;
    @BindView(R.id.biv_indicator)
    PointIndicatorView bivIndicator;

    @Override
    public int getLayoutId() {
        return R.layout.banner_activity;
    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void innitView() {
        vpViewPager.setPageTransformer(true, new CardPageTransformer());
        vpViewPager.setShowLeftAndRightPage();
        vpViewPager.setEntries(getImageBannerEntries());
        vpViewPager.setOnPageClickListener(new BannerView.OnPageClickListener() {
            @Override
            protected void onPageClick(BannerEntry entry, int index) {
                ToastTextUtils.showToast("点击了"+index);
            }
        });

    }

    @Override
    protected void innitData() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    public static void startActivity(Context context) {
        Intent i = new Intent(context, BannerActivity.class);
        context.startActivity(i);
    }

    @NonNull
    private List<ImageBannerEntry> getImageBannerEntries() {
        List<ImageBannerEntry> items = new ArrayList<>();
        items.add(new ImageBannerEntry("第一页", null, "http://m.qiyipic.com/common/lego/20171026/dd116655c96d4a249253167727ed37c8.jpg"));
        items.add(new ImageBannerEntry("第二页", null, "http://m.qiyipic.com/common/lego/20171029/c9c3800f35f84f1398b89740f80d8aa6.jpg"));
        items.add(new ImageBannerEntry("第三页", null, "http://m.qiyipic.com/common/lego/20171023/bd84e15d8dd44d7c9674218de30ac75c.jpg"));
        items.add(new ImageBannerEntry("第四页", null, "http://m.qiyipic.com/common/lego/20171028/f1b872de43e649ddbf624b1451ebf95e.jpg"));
        items.add(new ImageBannerEntry("第五页", null, "http://pic2.qiyipic.com/common/20171027/cdc6210c26e24f08940d36a5eb918c34.jpg"));
        return items;
    }


}
