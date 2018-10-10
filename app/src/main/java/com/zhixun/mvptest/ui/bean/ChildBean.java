package com.zhixun.mvptest.ui.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/7.
 */

public class ChildBean implements Serializable {
    private String child;

    public ChildBean() {
    }

    public ChildBean(String child) {
        this.child = child;
    }

    public String getChild() {
        return child == null ? "" : child;
    }

    public void setChild(String child) {
        this.child = child;
    }
}
