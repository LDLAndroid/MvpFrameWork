package com.zhixun.mvptest.api;

import com.zhixun.mvptest.base.BaseBean;
import com.zhixun.mvptest.base.FwsHttpParames;
import com.zhixun.mvptest.ui.bean.Banner;
import com.zhixun.mvptest.ui.bean.HomeOrderData;
import com.zhixun.mvptest.ui.bean.IsExistMsg;
import com.zhixun.mvptest.ui.bean.LimitBean;
import com.zhixun.mvptest.ui.bean.LoanUse;
import com.zhixun.mvptest.ui.bean.Marquee;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
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
     */
    @GET("order/queryOrderList")
    Observable<HomeOrderData> queryOrderList(@Header("sign") String sign, @Header("reqTime") String reqTime, @Header("accessKey") String accessKey,
                                             @QueryMap FwsHttpParames parames);

    /**
     * 轮播图
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
    @FormUrlEncoded
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
