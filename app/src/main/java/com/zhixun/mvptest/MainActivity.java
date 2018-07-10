package com.zhixun.mvptest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.zhixun.mvptest.base.BaseActivity;
import com.zhixun.mvptest.component.AppComponent;
import com.zhixun.mvptest.ui.fragment.AttentionFragment;
import com.zhixun.mvptest.ui.fragment.HomeFragment;
import com.zhixun.mvptest.ui.fragment.MineFragment;
import com.zhixun.mvptest.ui.fragment.ProductFragment;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {


    @BindView(R.id.fl_content)
    FrameLayout flContent;
    private ProductFragment productFragment;
    private HomeFragment homeFragment;
    private AttentionFragment attentionFragment;
    private MineFragment mineFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void innitView() {
        // mPresenter.queryAppUseParam();
        showHomeFragment();
    }

    @Override
    protected void innitData() {
        //NetworkFeedActivity.start(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
    }


    @OnClick({R.id.rbHome, R.id.rbLive, R.id.rbFollw, R.id.rbMe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rbHome:
                //首页
                showHomeFragment();
                break;
            case R.id.rbLive:
                //商城
                showProductFragment();
                break;
            case R.id.rbFollw:
                //关注
                showAttentionFragment();
                break;
            case R.id.rbMe:
                //我的
                showMineFragment();
                break;
        }
    }

    public void showHomeFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideAllFragment(ft);
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance();
            ft.add(R.id.fl_content, homeFragment);
        }
        commitShowFragment(ft, homeFragment);
    }

    public void showProductFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideAllFragment(ft);
        if (productFragment == null) {
            productFragment = ProductFragment.newInstance();
            ft.add(R.id.fl_content, productFragment);
        }
        commitShowFragment(ft, productFragment);
    }

    public void showAttentionFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideAllFragment(ft);
        if (attentionFragment == null) {
            attentionFragment = AttentionFragment.newInstance();
            ft.add(R.id.fl_content, attentionFragment);
        }
        commitShowFragment(ft, attentionFragment);
    }

    public void showMineFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideAllFragment(ft);
        if (mineFragment == null) {
            mineFragment = MineFragment.newInstance();
            ft.add(R.id.fl_content, mineFragment);
        }
        commitShowFragment(ft, mineFragment);
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        hideFragment(fragmentTransaction, homeFragment);
        hideFragment(fragmentTransaction, productFragment);
        hideFragment(fragmentTransaction, attentionFragment);
        hideFragment(fragmentTransaction, mineFragment);
    }

    private void hideFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
        }
    }

    public void commitShowFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }
}
