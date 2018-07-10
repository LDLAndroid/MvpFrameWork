package com.zhixun.mvptest.ui.bean;


import com.zhixun.mvptest.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class HomeOrderData extends BaseBean {
    private List<HomeOrderDataList> data;

    public List<HomeOrderDataList> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<HomeOrderDataList> data) {
        this.data = data;
    }
}
