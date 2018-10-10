package com.zhixun.mvptest.mvp.Contract;

import com.zhixun.mvptest.base.BaseBean;
import com.zhixun.mvptest.base.BaseContract;
import com.zhixun.mvptest.ui.bean.Announcement;
import com.zhixun.mvptest.ui.bean.Banner;
import com.zhixun.mvptest.ui.bean.HomeOrderData;
import com.zhixun.mvptest.ui.bean.LimitBean;

/**
 * Created by Administrator on 2017/7/5.
 */
public interface RefreshContract {
    interface View extends BaseContract.BaseView {
        void showqueryOrderList(HomeOrderData homeOrderData);
    }

    interface Presenter<T> extends BaseContract.BaesPresenter<T> {
        //首页借款用户列表数据
        void queryOrderList(int applyDaysStart, int applyDaysEnd, int limit, int offset, int sort, Number yearRateStart, Number yearRateEnd);
    }
}
