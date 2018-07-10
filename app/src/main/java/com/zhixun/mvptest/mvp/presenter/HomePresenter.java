package com.zhixun.mvptest.mvp.presenter;


import com.zhixun.mvptest.api.Api;
import com.zhixun.mvptest.base.BaseBean;
import com.zhixun.mvptest.base.Constant;
import com.zhixun.mvptest.base.RxPresenter;
import com.zhixun.mvptest.mvp.Contract.HomeContract;
import com.zhixun.mvptest.mvp.exception.BaseSubscriber;
import com.zhixun.mvptest.ui.bean.Banner;
import com.zhixun.mvptest.ui.bean.HomeOrderData;
import com.zhixun.mvptest.ui.bean.IsExistMsg;
import com.zhixun.mvptest.ui.bean.LimitBean;
import com.zhixun.mvptest.ui.bean.LoanUse;
import com.zhixun.mvptest.ui.bean.Marquee;
import com.zhixun.mvptest.utils.ToastTextUtils;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/4.
 */

public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter<HomeContract.View> {


    private Api api;

    @Inject
    public HomePresenter(Api api) {
        this.api = api;
    }


    @Override
    public void queryOrderList(int applyDaysStart, int applyDaysEnd, int limit, int offset, int sort, Number yearRateStart, Number yearRateEnd) {
        addSubscription(api.queryOrderList(applyDaysStart, applyDaysEnd, limit, offset, sort, yearRateStart, yearRateEnd)
                , new BaseSubscriber<HomeOrderData>() {

            @Override
            protected void onSuccess(HomeOrderData data) {
                mView.showqueryOrderList(data);
            }

            @Override
            public void onError(String message) {
                mView.showError(message);
            }

            @Override
            public void completed() {
                mView.complete();
            }
        });
    }

    @Override
    public void queryBanner(int type) {
        addSubscription(api.queryBanner(type), new BaseSubscriber<Banner>() {

            @Override
            protected void onSuccess(Banner data) {
                mView.showBanner(data);
            }

            @Override
            public void onError(String message) {
                mView.showError(message);
            }

            @Override
            public void completed() {
                mView.complete();
            }
        });
    }

    @Override
    public void receiveCoupon(String userId) {
        addSubscription(api.receiveCoupon(userId), new BaseSubscriber<BaseBean>() {

            @Override
            protected void onSuccess(BaseBean data) {
                mView.showreceiveCoupon(data);
            }

            @Override
            public void onError(String message) {
                mView.showError(message);
            }

            @Override
            public void completed() {
                mView.complete();
            }
        });
    }




    @Override
    public void queryAppUseParam() {
        addSubscription(api.queryAppUseParam(), new BaseSubscriber<LimitBean>() {

            @Override
            protected void onSuccess(LimitBean data) {
                mView.showqueryAppUseParam(data);
            }

            @Override
            public void onError(String message) {
                mView.showError(message);
            }

            @Override
            public void completed() {
                mView.complete();
            }
        });
    }

    @Override
    public void queryAnnouncement() {
        addSubscription(api.queryAppUseParam(), new BaseSubscriber<LimitBean>() {

            @Override
            protected void onSuccess(LimitBean data) {
                mView.showqueryAppUseParam(data);
            }

            @Override
            public void onError(String message) {
                mView.showError(message);
            }

            @Override
            public void completed() {
                mView.complete();
            }
        });
    }


}
