package com.zhixun.mvptest.mvp.exception;

/**
 * Created by Administrator on 2018/3/6.
 */

import android.content.Context;
import android.util.Log;


import com.zhixun.mvptest.App;
import com.zhixun.mvptest.base.AppManager;
import com.zhixun.mvptest.base.BaseBean;
import com.zhixun.mvptest.base.Constant;
import com.zhixun.mvptest.dialog.LoadingDialog;
import com.zhixun.mvptest.utils.ToastTextUtils;
import com.zhixun.mvptest.view.ZToast;

import rx.Subscriber;

/**
 * Subscriber基类,可以在这里处理client网络连接状况
 * （比如没有wifi，没有4g，没有联网等）
 * Created by fcn-mq on 2017/4/19.
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {
    private Context context;
    private LoadingDialog loadingDialog;

    public BaseSubscriber() {
        loadingDialog = new LoadingDialog(App.getInstance(), "加载中...");
        this.context = App.getInstance();

    }

    @Override
    public void onStart() {
        loadingDialog.show();
        super.onStart();
        Log.i("tag", "BaseSubscriber.onStart()");

    }

    @Override
    public void onError(Throwable e) {
        Log.e("tag", "BaseSubscriber.throwable =" + e.toString());
        Log.e("tag", "BaseSubscriber.throwable =" + e.getMessage());
        loadingDialog.dismiss();
        if (e instanceof Exception) {
            //访问获得对应的Exception
            ExceptionHandle.ResponeThrowable responeThrowable = ExceptionHandle.handleException(e);
            String message = responeThrowable.message;
            onError(message);
            if (message != null) {
                //ToastTextUtils.showToast(message);
                System.out.println("**********currentActivity************"+AppManager.getAppManager().currentActivity());
                ZToast.makeText(AppManager.getAppManager().currentActivity(),message,1000).show();
            }
        }
    }

    @Override
    public void onNext(T t) {
        if (t instanceof BaseBean) {
            if (((BaseBean) t).getStatus().equals(Constant.SUCCESS_CODE)) {
                onSuccess(t);
            } else if (((BaseBean) t).getStatus().equals(Constant.AGAIN_USER)) {
                //异地登录

            } else {
                ToastTextUtils.showToast(((BaseBean) t).getMessage());
            }
        }
    }

    protected abstract void onSuccess(T t);

    public abstract void onError(String message);

    @Override
    public void onCompleted() {
        loadingDialog.dismiss();
        completed();
    }

    public abstract void completed();
}
