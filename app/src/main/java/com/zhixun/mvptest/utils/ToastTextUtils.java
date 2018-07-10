package com.zhixun.mvptest.utils;

import android.widget.Toast;

import com.zhixun.mvptest.App;


/**
 * Created by Administrator on 2018/3/8.
 */

public class ToastTextUtils {
    private static String oldMsg;
    private static Toast toast;
    private static long oneTime = 0;
    private static long twoTime = 0;

    public static void toast(int resId) {
        showToast(App.getInstance().getString(resId));
    }

    public static void toast(String text) {
        showToast(text);
    }

    public static void showToast(String message) {
        if (toast == null) {
            toast = Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (message.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = message;
                toast.setText(message);
                toast.show();
            }
        }
        oneTime = twoTime;
    }
}
