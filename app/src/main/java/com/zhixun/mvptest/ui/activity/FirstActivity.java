package com.zhixun.mvptest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.example.zhouwei.library.CustomPopWindow;
import com.zhixun.mvptest.R;
import com.zhixun.mvptest.base.BaseActivity;
import com.zhixun.mvptest.component.AppComponent;
import com.zhixun.mvptest.view.CustomCircleView;
import com.zhixun.mvptest.view.HeartView;
import com.zhixun.mvptest.view.psw.PasswordEditText;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/6.
 */

public class FirstActivity extends BaseActivity {


    @BindView(R.id.password_edit_text)
    PasswordEditText passwordEditText;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.heart_view)
    HeartView heartView;
    @BindView(R.id.ll_title)
    RelativeLayout llTitle;
    private TextView tv_tab_title;
    private TextView tv_tab_title1;
    private CustomCircleView iv_tab_red;
    private TimePickerBuilder pvCustomLunar;
    private TimePickerBuilder pvTime;
    private CustomPopWindow.PopupWindowBuilder popupWindowBuilder;
    private View contentView1;
    private View contentView2;

    @Override
    public int getLayoutId() {
        return R.layout.first_activity;
    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void innitView() {
        passwordEditText.setOnPasswordFullListener(new PasswordEditText.OnPswInputFinishListener() {
            @Override
            public void pswInputFinish(String password) {
                closeSoftInput();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //openSoftInput();
    }

    @Override
    protected void innitData() {
        TabLayout.Tab tabAll = tabLayout.newTab().setCustomView(R.layout.red_point);
        tv_tab_title = tabAll.getCustomView().findViewById(R.id.tv_tab_title);
        tv_tab_title.setText("全部");
        tabLayout.addTab(tabAll);
        tabLayout.addTab(tabLayout.newTab().setText("待付款"));
        tabLayout.addTab(tabLayout.newTab().setText("已付款"));
        tabLayout.addTab(tabLayout.newTab().setText("已过期"));
        tabLayout.addTab(tabLayout.newTab().setText("已过期"));
        tabLayout.addTab(tabLayout.newTab().setText("已过期"));
        tabLayout.addTab(tabLayout.newTab().setText("已过期"));
        tabLayout.addTab(tabLayout.newTab().setText("已过期"));
        tabLayout.addTab(tabLayout.newTab().setText("已过期"));
        tabLayout.addTab(tabLayout.newTab().setText("已过期"));
//        TabLayout.Tab tab1 = tabLayout.getTabAt(1).setCustomView(R.layout.red_point);
//        iv_tab_red = tab1.getCustomView().findViewById(R.id.iv_tab_red);
//        tv_tab_title1 = tab1.getCustomView().findViewById(R.id.tv_tab_title);
//        iv_tab_red.setTitleText("26");
//        tv_tab_title1.setText("待付款");
        initPop();
    }

    private void initPop() {
        popupWindowBuilder = new CustomPopWindow.PopupWindowBuilder(this);
        contentView1 = LayoutInflater.from(this).inflate(R.layout.dropdown_content,null);
        contentView2 = LayoutInflater.from(this).inflate(R.layout.dropdown2_content,null);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    public static void startActivity(Context context) {
        Intent i = new Intent(context, FirstActivity.class);
        context.startActivity(i);
    }

    public void openSoftInput() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(passwordEditText, 0);
            }

        }, 300);
    }

    public void closeSoftInput() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(passwordEditText.getWindowToken(), 0);
            }

        }, 300);
    }

    @OnClick({R.id.btn_gettext, R.id.password_edit_text, R.id.btn_click, R.id.ll_dropmenu, R.id.ll_sort, R.id.ll_filter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_gettext:
                passwordEditText.setText("");
                break;
            case R.id.btn_click:
                heartView.startAnimator();
                break;
            case R.id.ll_dropmenu:
                //下拉筛选
                //  DropmenuActivity.startActivity(this);
                //  BannerActivity.startActivity(this);
                showDialogFragment();
                break;
            case R.id.ll_sort:
                popupWindowBuilder.setView(contentView1)
                        .setFocusable(true)//是否获取焦点，默认为ture
                        .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                        .create()//创建PopupWindow
                        .showAsDropDown(llTitle, 0, 0);//显示PopupWindow
                break;
            case R.id.ll_filter:
                popupWindowBuilder.setView(contentView2)
                        .setFocusable(true)//是否获取焦点，默认为ture
                        .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                        .create()//创建PopupWindow
                        .showAsDropDown(llTitle, 0, 0);//显示PopupWindow
                break;
        }
    }

    private void showDialogFragment() {
    }

}
