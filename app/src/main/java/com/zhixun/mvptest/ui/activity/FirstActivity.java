package com.zhixun.mvptest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhixun.mvptest.R;
import com.zhixun.mvptest.base.BaseActivity;
import com.zhixun.mvptest.component.AppComponent;
import com.zhixun.mvptest.utils.CommonUtils;
import com.zhixun.mvptest.utils.ToastTextUtils;
import com.zhixun.mvptest.view.PasswordInputView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/6.
 */

public class FirstActivity extends BaseActivity {
    @BindView(R.id.psw)
    PasswordInputView psw;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_attention;
    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void innitView() {
        int viewHeight = CommonUtils.getViewHeight(psw, true);
        psw.setTextSize(37);
        psw.setOnFinishListener(new PasswordInputView.OnFinishListener() {
            @Override
            public void setOnPasswordFinished() {
                //ToastTextUtils.showToast(psw.getText().toString());
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
        Intent i = new Intent(context, FirstActivity.class);
        context.startActivity(i);
    }

    @OnClick({R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clear:
                ToastTextUtils.showToast(psw.getOriginText());
                //psw.clearText();
                break;
        }
    }
}
