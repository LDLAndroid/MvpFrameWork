package com.zhixun.mvptest.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.zhixun.mvptest.R;



/**
 * Created by Administrator on 2016/11/22.
 */

public class LoadingDialog extends Dialog {


    private String title;


    public LoadingDialog(Context context, String title) {
        super(context, R.style.MNCustomProgressDialog);
        this.title = title;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        //ButterKnife.bind(this);
        getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        setCanceledOnTouchOutside(false);
        if (title.equals("")) {
            //tvTitle.setVisibility(View.GONE);
        } else {
            //tvTitle.setText(title);
        }
    }


}
