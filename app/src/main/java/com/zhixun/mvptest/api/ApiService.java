package com.zhixun.mvptest.api;

import com.zhixun.mvptest.base.BaseBean;
import com.zhixun.mvptest.ui.bean.Banner;
import com.zhixun.mvptest.ui.bean.HomeOrderData;
import com.zhixun.mvptest.ui.bean.IsExistMsg;
import com.zhixun.mvptest.ui.bean.LimitBean;
import com.zhixun.mvptest.ui.bean.LoanUse;
import com.zhixun.mvptest.ui.bean.Marquee;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/6/20.
 */

public interface ApiService {
    @GET("dataDictionary/queryAppUseParam")
    Observable<LimitBean> queryAppUseParam(@Header("sign") String sign, @Header("reqTime") String reqTime);

    /**
     * 借款用途
     *
     * @param sign
     * @param reqTime
     * @param accessKey
     * @return
     */
    @GET("order/queryOrderLoanUsageList")
    Observable<LoanUse> queryOrderLoanUsageList(@Header("sign") String sign, @Header("reqTime") String reqTime, @Header("accessKey") String accessKey);

    /**
     * 首页列表数据
     *
     * @param applyDaysStart 借款申请期限开始
     * @param applyDaysEnd   借款申请期限结束
     * @param limit          分页多少行
     * @param page           分页起始页
     * @param sort           0 默认排序 1期限从短到长 2期限从长到短 3利率从高到低 4 利率从低到高
     * @param yearRateStart  利率开始
     * @param yearRateEnd    利率结束
     */
    @GET("order/queryOrderList")
    Observable<HomeOrderData> queryOrderList(@Header("sign") String sign, @Header("reqTime") String reqTime, @Header("accessKey") String accessKey,
                                             @Query("applyDaysStart") int applyDaysStart, @Query("applyDaysEnd") int applyDaysEnd, @Query("limit") int limit,
                                             @Query("page") int page, @Query("sort") int sort, @Query("yearRateStart") Number yearRateStart,
                                             @Query("yearRateEnd") Number yearRateEnd);

    /**
     * 轮播图
     *
     * @param sign
     * @param reqTime
     * @param accessKey
     * @param type
     * @return
     */
    @GET("sms/queryBanner")
    Observable<Banner> queryBanner(@Header("sign") String sign, @Header("reqTime") String reqTime, @Header("accessKey") String accessKey,
                                   @Query("type") int type);

    /**
     * 领取优惠券
     *
     * @param sign
     * @param reqTime
     * @param accessKey
     * @param userId
     * @return
     */
    @GET("user/receiveCoupon")
    Observable<BaseBean> receiveCoupon(@Header("sign") String sign, @Header("reqTime") String reqTime, @Header("accessKey") String accessKey,
                                       @Query("userId") String userId);

    /**
     * 快讯
     *
     * @param sign
     * @param reqTime
     * @param accessKey
     * @return
     */
    @POST("order/marquee")
    Observable<Marquee> marquee(@Header("sign") String sign, @Header("reqTime") String reqTime, @Header("accessKey") String accessKey);

    /**
     * 是否存在未读消息
     *
     * @param sign
     * @param accessKey
     * @param userId
     * @return
     */
    @GET("sms/isExistPushInfo")
    Observable<IsExistMsg> isExistPushInfo(@Header("sign") String sign, @Header("reqTime") String reqTime, @Header("accessKey") String accessKey,
                                           @Query("userId") String userId);
}
