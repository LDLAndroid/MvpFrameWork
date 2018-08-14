package com.zhixun.mvptest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.zhixun.mvptest.MainActivity;
import com.zhixun.mvptest.R;
import com.zhixun.mvptest.base.BaseActivity;
import com.zhixun.mvptest.component.AppComponent;
import com.zhixun.mvptest.utils.TimeUtils;
import com.zhixun.mvptest.utils.ToastTextUtils;
import com.zhixun.mvptest.view.CustomCircleView;
import com.zhixun.mvptest.view.HeartView;
import com.zhixun.mvptest.view.psw.PasswordEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    private TextView tv_tab_title;
    private TextView tv_tab_title1;
    private CustomCircleView iv_tab_red;
    private TimePickerBuilder pvCustomLunar;
    private TimePickerBuilder pvTime;

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

    @OnClick({R.id.btn_gettext, R.id.password_edit_text, R.id.btn_click})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_gettext:
                passwordEditText.setText("");
                break;
            case R.id.btn_click:
                heartView.startAnimator();
                break;
        }
    }
}
