package com.zhixun.mvptest.ui.bean;


import com.zhixun.mvptest.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/27.
 */

public class LoanUse extends BaseBean {
    private List<LoanUseData> data;

    public List<LoanUseData> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<LoanUseData> data) {
        this.data = data;
    }
}
