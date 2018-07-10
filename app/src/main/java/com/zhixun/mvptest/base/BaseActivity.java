package com.zhixun.mvptest.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;
import com.zhixun.mvptest.App;
import com.zhixun.mvptest.component.AppComponent;
import com.zhixun.mvptest.dialog.LoadingDialog;

import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by Administrator on 2017/5/3.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private LoadingDialog loadingDialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
       ButterKnife.bind(this);
        App instance = App.getInstance();
        setupActivityComponent(instance.getAppComponent());
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        attachView();
        innitView();
        innitData();
        //将Activity实例添加到AppManager的堆栈
        AppManager.getAppManager().addActivity(this);
    }


    /**
     * 获取当前activity布局
     *
     * @return
     */
    public abstract int getLayoutId();

    protected abstract void attachView();

    /**
     * 初始化控件
     */
    protected abstract void innitView();

    /**
     * 初始化数据
     */
    protected abstract void innitData();

    protected abstract void setupActivityComponent(AppComponent appComponent);


    /**
     * 进入界面弹出加载中的dialog
     *
     * @param text 弹出的文字
     */
    protected void showDialog(String text) {
        loadingDialog = new LoadingDialog(this, text);
        loadingDialog.show();
    }


    /**
     * 隐藏diaolog
     */
    public void dismissDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }


    //解决崩溃后重新打开程序，fragment 重叠问题
    //当前APP崩溃再次启动或者从后台再次回到这个app的时候，通过onCreate中的参数savedInstanceState恢复了之前的fragment。
    // 此时的FragmentTransaction中的相当于又再次add了fragment进去的，之前保存的fragment也还在。hide()和show()方法对
    // 之前保存的fragment已经失效了。所以出现了重叠的现象
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }

    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    @CallSuper
    protected void onPause() {
        super.onPause();
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
    }

    @Override
    @CallSuper
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }
}
