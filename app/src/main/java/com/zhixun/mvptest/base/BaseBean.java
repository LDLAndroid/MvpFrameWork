package com.zhixun.mvptest.base;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by Administrator on 2017/5/3.
 */
@Data
public class BaseBean implements Serializable {
    private String message;
    private String status;
    private boolean rel;

}
