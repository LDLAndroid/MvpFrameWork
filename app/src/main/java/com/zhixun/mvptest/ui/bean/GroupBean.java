package com.zhixun.mvptest.ui.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/7.
 */

public class GroupBean implements Serializable{
    private String title;

    public GroupBean() {
    }

    public GroupBean(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
