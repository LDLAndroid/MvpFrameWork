package com.zhixun.mvptest.ui.bean;


import com.zhixun.mvptest.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/12.
 */

public class Announcement extends BaseBean {
    private List<AnnouncementData> data;

    public List<AnnouncementData> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<AnnouncementData> data) {
        this.data = data;
    }
}
