package com.zhixun.mvptest.mvp.presenter;


import com.zhixun.mvptest.api.Api;
import com.zhixun.mvptest.base.BaseBean;
import com.zhixun.mvptest.base.RxPresenter;
import com.zhixun.mvptest.mvp.Contract.HomeContract;
import com.zhixun.mvptest.mvp.Contract.RefreshContract;
import com.zhixun.mvptest.mvp.exception.BaseSubscriber;
import com.zhixun.mvptest.ui.bean.Banner;
import com.zhixun.mvptest.ui.bean.HomeOrderData;
import com.zhixun.mvptest.ui.bean.LimitBean;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/5/4.
 */

public class FreshPresenter extends RxPresenter<RefreshContract.View> implements RefreshContract.Presenter<RefreshContract.View> {


    private Api api;

    @Inject
    public FreshPresenter(Api api) {
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

}
