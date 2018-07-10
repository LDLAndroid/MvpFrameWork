package com.zhixun.mvptest.ui.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/4.
 */

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

    public double getActualAccountAmount() {
        return actualAccountAmount;
    }

    public void setActualAccountAmount(double actualAccountAmount) {
        this.actualAccountAmount = actualAccountAmount;
    }

    public double getActualReimAmount() {
        return actualReimAmount;
    }

    public void setActualReimAmount(double actualReimAmount) {
        this.actualReimAmount = actualReimAmount;
    }

    public int getApplyDays() {
        return applyDays;
    }

    public void setApplyDays(int applyDays) {
        this.applyDays = applyDays;
    }

    public String getBorrowerUserId() {
        return borrowerUserId == null ? "" : borrowerUserId;
    }

    public void setBorrowerUserId(String borrowerUserId) {
        this.borrowerUserId = borrowerUserId;
    }

    public String getBorrowerUserName() {
        return borrowerUserName == null ? "" : borrowerUserName;
    }

    public void setBorrowerUserName(String borrowerUserName) {
        this.borrowerUserName = borrowerUserName;
    }

    public double getBorrowingAmount() {
        return borrowingAmount;
    }

    public void setBorrowingAmount(double borrowingAmount) {
        this.borrowingAmount = borrowingAmount;
    }

    public String getGmtCreate() {
        return gmtCreate == null ? "" : gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModify() {
        return gmtModify == null ? "" : gmtModify;
    }

    public void setGmtModify(String gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLateAmount() {
        return lateAmount;
    }

    public void setLateAmount(double lateAmount) {
        this.lateAmount = lateAmount;
    }

    public String getLenderUserId() {
        return lenderUserId == null ? "" : lenderUserId;
    }

    public void setLenderUserId(String lenderUserId) {
        this.lenderUserId = lenderUserId;
    }

    public String getLendingDate() {
        return lendingDate == null ? "" : lendingDate;
    }

    public void setLendingDate(String lendingDate) {
        this.lendingDate = lendingDate;
    }

    public String getOnsumptionSceneTypeStr() {
        return onsumptionSceneTypeStr == null ? "" : onsumptionSceneTypeStr;
    }

    public void setOnsumptionSceneTypeStr(String onsumptionSceneTypeStr) {
        this.onsumptionSceneTypeStr = onsumptionSceneTypeStr;
    }

    public double getPoundageAmount() {
        return poundageAmount;
    }

    public void setPoundageAmount(double poundageAmount) {
        this.poundageAmount = poundageAmount;
    }

    public double getPreferentialAmount() {
        return preferentialAmount;
    }

    public void setPreferentialAmount(double preferentialAmount) {
        this.preferentialAmount = preferentialAmount;
    }

    public String getReimDate() {
        return reimDate == null ? "" : reimDate;
    }

    public void setReimDate(String reimDate) {
        this.reimDate = reimDate;
    }

    public double getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(double rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getStillAmount() {
        return stillAmount;
    }

    public void setStillAmount(double stillAmount) {
        this.stillAmount = stillAmount;
    }

    public int getTimeoutDay() {
        return timeoutDay;
    }

    public void setTimeoutDay(int timeoutDay) {
        this.timeoutDay = timeoutDay;
    }

    public double getYearRate() {
        return yearRate;
    }

    public void setYearRate(double yearRate) {
        this.yearRate = yearRate;
    }

    public String getHeadPortrait() {
        return headPortrait == null ? "" : headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }
}
