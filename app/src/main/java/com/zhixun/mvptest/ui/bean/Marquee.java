package com.zhixun.mvptest.ui.bean;


import com.zhixun.mvptest.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/28.
 */

public class Marquee extends BaseBean {
    private List<String> data;

    public List<String> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
