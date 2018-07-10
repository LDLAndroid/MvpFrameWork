package com.zhixun.mvptest.component;



import android.content.Context;

import com.zhixun.mvptest.api.Api;
import com.zhixun.mvptest.module.ApiModule;
import com.zhixun.mvptest.module.AppModule;

import dagger.Component;

/** Component(组件)
 * Created by Administrator on 2016/11/7.
 */
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    Context getContext();

    Api getApi();

}
