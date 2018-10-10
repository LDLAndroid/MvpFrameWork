package com.zhixun.mvptest.ui.fragment.BottomFrament;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mic.bottomsheetlib.BottomSheetSettingsBuilder;
import com.mic.bottomsheetlib.interfaces.BottomSheetDialogInterface;
import com.mic.bottomsheetlib.utils.BottomSheetTitleSetting;
import com.zhixun.mvptest.R;
import com.zhixun.mvptest.base.BaseFragment;
import com.zhixun.mvptest.component.AppComponent;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/8/24.
 */

@SuppressLint("ValidFragment")
public class Bottom1Fragment extends BaseFragment {
    private final BottomSheetDialogInterface builder;

    public Bottom1Fragment(BottomSheetDialogInterface builder) {
        super();
        this.builder = builder;
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    public void attachView() {
    }

    @Override
    public int getLayoutResId() {
        return R.layout.bottom_fragment1;
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


    @OnClick({R.id.iv_cancle, R.id.rl_use_coupon, R.id.rl_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_cancle:
                builder.cancel();
                break;
            case R.id.rl_use_coupon:
                builder.push(new Bottom2Fragment(builder), new BottomSheetTitleSetting().setTitleVisible(false));
                break;
            case R.id.rl_sure:
                builder.push(new Bottom3Fragment(builder), new BottomSheetTitleSetting().setTitleVisible(false));
                break;
        }
    }
}
