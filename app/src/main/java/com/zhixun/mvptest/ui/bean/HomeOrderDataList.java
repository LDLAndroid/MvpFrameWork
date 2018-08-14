package com.zhixun.mvptest.ui.bean;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Administrator on 2018/4/4.
 */

@Data
public class HomeOrderDataList implements Serializable {
    private double actualAccountAmount;//实际到帐金额
    private double actualReimAmount;//	实际还款金额
    private int applyDays;//申请期限,天数
    private String borrowerUserId;    //借款用户id
    private String borrowerUserName;    //借款用户姓名
    private double borrowingAmount;//	借贷金额
    private String gmtCreate;    //	string
    private String gmtModify;    //	string
    private String id;    //string
    private double lateAmount;//	滞纳金
    private String lenderUserId;    //放款用户ID
    private String lendingDate;//放款日期
    private String onsumptionSceneTypeStr;//	消费场景
    private double poundageAmount;//	手续费
    private double preferentialAmount;    //折扣金额
    private String reimDate;    //还款日期
    private double rewardAmount;//	打赏费
    private int status;//状态 -1订单关闭，0待发布，1已发布，2 已过期， 3等待放款， 4放款失败， 5放款成功， 6待	number
    private double stillAmount;//	当前还需还款金额
    private int timeoutDay;//	逾期天数
    private double yearRate;    //年化利率
    private String headPortrait;//头像

}
