package com.zhixun.mvptest.api;

import com.google.gson.Gson;
import com.zhixun.mvptest.base.BaseBean;
import com.zhixun.mvptest.base.Constant;
import com.zhixun.mvptest.base.FwsHttpParames;
import com.zhixun.mvptest.ui.bean.Banner;
import com.zhixun.mvptest.ui.bean.HomeOrderData;
import com.zhixun.mvptest.ui.bean.IsExistMsg;
import com.zhixun.mvptest.ui.bean.LimitBean;
import com.zhixun.mvptest.ui.bean.LoanUse;
import com.zhixun.mvptest.ui.bean.Marquee;
import com.zhixun.mvptest.utils.CommonUtils;
import com.zhixun.mvptest.utils.TimeUtils;

import java.util.Date;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;

/**
 * Created by Administrator on 2017/9/26.
 */

public class Api {
    public static Api api;
    private final ApiService apiService;

    public Api(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://139.196.210.150:12345/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    /**
     * 单例模式
     *
     * @param okHttpClient
     * @return
     */

    public static Api getInstance(OkHttpClient okHttpClient) {
        if (okHttpClient != null) {
            api = new Api(okHttpClient);
        }
        return api;
    }

    public Observable<LimitBean> queryAppUseParam() {
        FwsHttpParames parames = new FwsHttpParames();
        String time = TimeUtils.getSingTime(new Date());
        String sign = CommonUtils.generateSign(parames, time);
        return apiService.queryAppUseParam(sign, time);
    }

    /**
     * 借款用途
     *
     * @return
     */
    public Observable<LoanUse> queryOrderLoanUsageList() {
        FwsHttpParames parames = new FwsHttpParames();
        String time = TimeUtils.getSingTime(new Date());
        String sign = CommonUtils.generateSign(parames, time);
        return apiService.queryOrderLoanUsageList(sign, time, Constant.accessKey);
    }

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
    public Observable<HomeOrderData> queryOrderList(int applyDaysStart, int applyDaysEnd, int limit,
                                                    int page, int sort, Number yearRateStart, Number yearRateEnd) {
        FwsHttpParames parames = new FwsHttpParames();
        parames.put("applyDaysStart", applyDaysStart);
        parames.put("applyDaysEnd", applyDaysEnd);
        parames.put("limit", limit);
        parames.put("page", page);
        parames.put("sort", sort);
        parames.put("yearRateStart", yearRateStart);
        parames.put("yearRateEnd", yearRateEnd);
        String time = TimeUtils.getSingTime(new Date());
        String sign = CommonUtils.generateSign(parames, time);
        return apiService.queryOrderList(sign, time, Constant.accessKey,parames);
    }

    /**
     * banner图
     *
     * @param type
     * @return
     */
    public Observable<Banner> queryBanner(int type) {
        FwsHttpParames parames = new FwsHttpParames();
        parames.put("type", type);
        String time = TimeUtils.getSingTime(new Date());
        String sign = CommonUtils.generateSign(parames, time);
        return apiService.queryBanner(sign, time, Constant.accessKey, type);
    }
    /**
     * 获取优惠券
     *
     * @param userId
     * @return
     */
    public Observable<BaseBean> receiveCoupon(String userId) {
        FwsHttpParames parames = new FwsHttpParames();
        parames.put("userId", userId);
        String time = TimeUtils.getSingTime(new Date());
        String sign = CommonUtils.generateSign(parames, time);
        return apiService.receiveCoupon(sign, time, Constant.accessKey, userId);
    }

    /**
     * 快讯
     *
     * @return
     */
    public Observable<Marquee> marquee() {
        FwsHttpParames parames = new FwsHttpParames();
        String time = TimeUtils.getSingTime(new Date());
        String sign = CommonUtils.generateSign(parames, time);
        return apiService.marquee(sign, time, Constant.accessKey);
    }


    /**
     * 是否有未读消息
     * @param userId
     * @return
     */
    public Observable<IsExistMsg> isExistPushInfo(String userId) {
        FwsHttpParames parames = new FwsHttpParames();
        parames.put("userId", userId);
        String time = TimeUtils.getSingTime(new Date());
        String sign = CommonUtils.generateSign(parames, time);
        return apiService.isExistPushInfo(sign, time, Constant.accessKey, userId);
    }
}
