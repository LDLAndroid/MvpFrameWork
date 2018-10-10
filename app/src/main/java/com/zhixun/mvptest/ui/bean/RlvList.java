package com.zhixun.mvptest.ui.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/7.
 */

public class RlvList implements Serializable{
    private String dayCount;
    private String sellerMoney;

    public String getDayCount() {
        return dayCount == null ? "" : dayCount;
    }

    public void setDayCount(String dayCount) {
        this.dayCount = dayCount;
    }

    public String getSellerMoney() {
        return sellerMoney == null ? "" : sellerMoney;
    }

    public void setSellerMoney(String sellerMoney) {
        this.sellerMoney = sellerMoney;
    }
}
