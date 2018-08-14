package com.zhixun.mvptest.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhixun.mvptest.R;
import com.zhixun.mvptest.view.anim.NewsPaper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/22.
 */

public class WarnDialog extends Dialog {


    private final String title;
    private final String cancle;
    private final String sure;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_quxiao)
    TextView tvQuxiao;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_queding)
    TextView tvQueding;
    @BindView(R.id.main)
    LinearLayout main;

    public WarnDialog(Context context, String title, String cancle, String sure) {
        super(context, R.style.MyDialogStyleTop);
        this.title = title;
        this.cancle = cancle;
        this.sure = sure;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_attention);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
        if (!title.isEmpty()) {
            tvTitle.setText(title);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
        if (!sure.isEmpty()) {
            tvQueding.setText(sure);
        } else {
            line.setVisibility(View.GONE);
            tvQueding.setVisibility(View.GONE);
        }

        if (!cancle.isEmpty()) {
            tvQuxiao.setText(cancle);
        } else {
            line.setVisibility(View.GONE);
            tvQuxiao.setVisibility(View.GONE);
        }
//        this.setOnShowListener(new OnShowListener() {
//            @Override
//            public void onShow(DialogInterface dialogInterface) {
//                NewsPaper newsPaper = new NewsPaper();
//                newsPaper.setDuration(200);
//                newsPaper.start(main);
//            }
//        });
    }

    @OnClick({R.id.tv_quxiao, R.id.tv_queding})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_quxiao:
                onCancleClick();
                break;
            case R.id.tv_queding:
                onOkClick();
                break;
        }
    }


    public void onCancleClick() {
        dismiss();
    }

    public void onOkClick() {
        dismiss();
    }

}
