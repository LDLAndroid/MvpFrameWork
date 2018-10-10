package com.zhixun.mvptest.utils;

import android.content.Context;

import com.zhixun.mvptest.dialog.WarnDialog;


/**
 * Created by Administrator on 2018/4/11.
 */

public class DialogUtil {


    private static WarnDialog warnDialog;

    public static void showDialog(Context mContext, String title) {
        warnDialog = new WarnDialog(mContext, title, "", "确定");
        if (!warnDialog.isShowing()) {
                warnDialog.show();
        }else{
            warnDialog.dismiss();
        }
    }

    public static void dismissDialog() {
        if (warnDialog != null) {
            warnDialog.dismiss();
        }
    }
}
