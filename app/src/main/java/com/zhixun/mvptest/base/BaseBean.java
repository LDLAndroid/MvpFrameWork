package com.zhixun.mvptest.base;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/3.
 */

public class BaseBean implements Serializable {
    private String message;
    private String status;
    private boolean rel;

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }
}
