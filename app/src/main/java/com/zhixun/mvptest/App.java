package com.zhixun.mvptest;

import android.app.Application;
import android.util.Log;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.PermissionListener;
import com.yhao.floatwindow.Screen;
import com.yhao.floatwindow.ViewStateListener;
import com.zhixun.mvptest.component.AppComponent;
import com.zhixun.mvptest.component.DaggerAppComponent;
import com.zhixun.mvptest.module.ApiModule;
import com.zhixun.mvptest.module.AppModule;


/**
 * Created by Administrator on 2018/6/20.
 */

public class App extends Application {
    private static final String TAG = "App";
    private static App instance;
    private AppComponent appComponent;

//    /**
//     * 全局设置下拉刷新的头 和脚
//     */
//    static {
//        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
//            @NonNull
//            @Override
//            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
//                return new FFRefreshHeader(instance);
//            }
//        });
//        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
//            @NonNull
//            @Override
//            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
//                return new ClassicsFooter(instance);
//            }
//        });
//    }
    @Override
    public void onCreate() {
        super.onCreate();
        this.instance=this;
        initComponent();
        //任意界面悬浮窗
       // initFloatWindow();
    }

    private void initFloatWindow() {
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(R.mipmap.avatar);
        FloatWindow
                .with(getApplicationContext())
                .setView(imageView)
                .setWidth(Screen.width, 0.2f) //设置悬浮控件宽高
                .setHeight(Screen.width, 0.2f)
                .setX(Screen.width, 0.8f)
                .setY(Screen.height, 0.3f)
                .setMoveType(MoveType.back,50,50)
                .setMoveStyle(500, new BounceInterpolator())
                .setFilter(true, MainActivity.class)
                .setViewStateListener(mViewStateListener)
                .setPermissionListener(mPermissionListener)
                .setDesktopShow(true)
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void initComponent() {
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    private PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onSuccess() {
            Log.d(TAG, "onSuccess");
        }

        @Override
        public void onFail() {
            Log.d(TAG, "onFail");
        }
    };

    private ViewStateListener mViewStateListener = new ViewStateListener() {
        @Override
        public void onPositionUpdate(int x, int y) {
            Log.d(TAG, "onPositionUpdate: x=" + x + " y=" + y);
        }

        @Override
        public void onShow() {
            Log.d(TAG, "onShow");
        }

        @Override
        public void onHide() {
            Log.d(TAG, "onHide");
        }

        @Override
        public void onDismiss() {
            Log.d(TAG, "onDismiss");
        }

        @Override
        public void onMoveAnimStart() {
            Log.d(TAG, "onMoveAnimStart");
        }

        @Override
        public void onMoveAnimEnd() {
            Log.d(TAG, "onMoveAnimEnd");
        }

        @Override
        public void onBackToDesktop() {
            Log.d(TAG, "onBackToDesktop");
        }
    };

}
