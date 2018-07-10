package com.zhixun.mvptest.mvp.Contract;

import com.zhixun.mvptest.base.BaseBean;
import com.zhixun.mvptest.base.BaseContract;
import com.zhixun.mvptest.ui.bean.Announcement;
import com.zhixun.mvptest.ui.bean.Banner;
import com.zhixun.mvptest.ui.bean.HomeOrderData;
import com.zhixun.mvptest.ui.bean.IsExistMsg;
import com.zhixun.mvptest.ui.bean.LimitBean;
import com.zhixun.mvptest.ui.bean.Marquee;

/**
 * Created by Administrator on 2017/7/5.
 */
public interface HomeContract {
    interface View extends BaseContract.BaseView {
        void showqueryOrderList(HomeOrderData homeOrderData);
        void showBanner(Banner banner);
        void showreceiveCoupon(BaseBean baseBean);
       // void showIsExistPushInfo(IsExistMsg isExistMsg);
        void showqueryAppUseParam(LimitBean limitBean);
        void showAnnouncement(Announcement announcement);
    }

    interface Presenter<T> extends BaseContract.BaesPresenter<T> {
        //首页借款用户列表数据
        void queryOrderList(int applyDaysStart, int applyDaysEnd, int limit, int offset, int sort, Number yearRateStart, Number yearRateEnd);
        //banner图
        void queryBanner(int type);
        void receiveCoupon(String userId);
        //是否存在未读消息
        //void isExistPushInfo(String userId);
        //获取数据字典
        void queryAppUseParam();
        //查询所有的公告
        void queryAnnouncement();
    }
}
