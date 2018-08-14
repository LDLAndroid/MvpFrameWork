package com.zhixun.mvptest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.zhixun.mvptest.R;
import com.zhixun.mvptest.base.BaseActivity;
import com.zhixun.mvptest.component.AppComponent;
import com.zhixun.mvptest.utils.ToastTextUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/3.
 */

public class ProductDetailsActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.tvtvt)
    Button tvtvt;
    @BindView(R.id.tv_dialog)
    Button tvDialog;
    @BindView(R.id.cb_eat)
    CheckBox cbEat;
    @BindView(R.id.cb_sleep)
    CheckBox cbSleep;
    @BindView(R.id.cb_water)
    CheckBox cbWater;
    private int eat = 0;
    private int sleep = 0;
    private int water = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_details;
    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void innitView() {
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }

    @Override
    protected void innitData() {
        setListener();
    }

    private void setListener() {
        cbEat.setOnCheckedChangeListener(this);
        cbSleep.setOnCheckedChangeListener(this);
        cbWater.setOnCheckedChangeListener(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    public static void startActivity(Context context) {
        Intent i = new Intent(context, ProductDetailsActivity.class);
        context.startActivity(i);
    }


    @OnClick({R.id.tvtvt, R.id.tv_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvtvt:
                QMUIPopup qmuiPopup = new QMUIPopup(this, QMUIPopup.DIRECTION_TOP);
                TextView textView = new TextView(this);
                textView.setLayoutParams(qmuiPopup.generateLayoutParam(
                        QMUIDisplayHelper.dp2px(this, 250),
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                textView.setLineSpacing(QMUIDisplayHelper.dp2px(this, 4), 1.0f);
                int padding = QMUIDisplayHelper.dp2px(this, 20);
                textView.setPadding(padding, padding, padding, padding);
                textView.setText("Popup 可以设置其位置以及显示和隐藏的动画");
                textView.setTextColor(ContextCompat.getColor(this, R.color.c_f95e49));
                qmuiPopup.setContentView(textView);//设置在QMUIPopup上面显示的内容或控件
                qmuiPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);//设置显示样式
                qmuiPopup.setPreferredDirection(QMUIPopup.ANIM_GROW_FROM_LEFT);//设置显示位置
                qmuiPopup.show(tvtvt);//设置在哪个控件上显示QMUIpopup
                break;
            case R.id.tv_dialog:
                System.out.println("eat=" + eat + ";sleep=" + sleep + ";water=" + water);
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
        if (compoundButton.getId() == R.id.cb_eat) {
            if (ischecked) {
                eat = 1;
            }else{
                eat = 0;
            }
        }
        if (compoundButton.getId() == R.id.cb_sleep) {
            if(ischecked){
                sleep = 1;
            }else{
                sleep = 0;
            }
        }
        if (compoundButton.getId() == R.id.cb_water) {
            if(ischecked){
                water = 1;
            }else{
                water = 0;
            }
        }
    }
}
