package com.zhixun.mvptest.ui.bean;


import com.zhixun.mvptest.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/9.
 */

public class Banner extends BaseBean {
    private List<BannerData> data;

    public List<BannerData> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<BannerData> data) {
        this.data = data;
    }
}
