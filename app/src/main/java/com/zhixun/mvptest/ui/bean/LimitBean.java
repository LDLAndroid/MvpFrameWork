package com.zhixun.mvptest.ui.bean;


import com.zhixun.mvptest.base.BaseBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/5/25.
 */

public class LimitBean extends BaseBean {

    // loanPublishTimeLimit	借款发布期限
    // maxLoanTimes	最大借款天数
    // maxRechargeQuota	充值最大限额
    //maxWithDrawQuota	提现最大限额
    //minLoanTimes	最低借款天数
    // minRechargeQuota	充值最低限额
    // minRewardQuota	打赏最低限额
   // minWithDrawQuota	提现最低限额
    //collectionMobile   催收电话
    // serviceQQ   客服QQ
    private Map<String,String> data ;

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
