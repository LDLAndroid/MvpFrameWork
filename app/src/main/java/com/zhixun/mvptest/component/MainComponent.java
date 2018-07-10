package com.zhixun.mvptest.component;


import com.zhixun.mvptest.MainActivity;
import com.zhixun.mvptest.ui.fragment.HomeFragment;
import com.zhixun.mvptest.ui.fragment.ProductFragment;

import dagger.Component;

/**
 * 用@Component表示这个接口是一个连接器，能用@Component注解的只能是interface或者抽象类
 * Created by Administrator on 2017/5/4.
 */


@Component(dependencies = AppComponent.class)
public interface MainComponent {


    HomeFragment inject(HomeFragment homeFragment);

}
