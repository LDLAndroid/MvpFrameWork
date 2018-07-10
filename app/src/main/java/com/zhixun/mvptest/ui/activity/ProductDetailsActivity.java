package com.zhixun.mvptest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.zhixun.mvptest.R;
import com.zhixun.mvptest.base.BaseActivity;
import com.zhixun.mvptest.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/3.
 */

public class ProductDetailsActivity extends BaseActivity {
    @BindView(R.id.tvtvt)
    Button tvtvt;

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

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    public static void startActivity(Context context) {
        Intent i = new Intent(context, ProductDetailsActivity.class);
        context.startActivity(i);
    }


    @OnClick({R.id.tvtvt,R.id.dialog})
    public void onViewClicked(View view) {
        switch (view.getId()){
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
            case R.id.dialog:

                break;
        }

    }

}
