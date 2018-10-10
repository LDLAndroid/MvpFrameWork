package com.zhixun.mvptest.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhixun.mvptest.R;
import com.zhixun.mvptest.adapter.HomeAdapter;
import com.zhixun.mvptest.adapter.HomeCarouselAdapter;
import com.zhixun.mvptest.adapter.SortAdapter;
import com.zhixun.mvptest.base.BaseBean;
import com.zhixun.mvptest.base.BaseRVFragment;
import com.zhixun.mvptest.component.AppComponent;
import com.zhixun.mvptest.component.DaggerMainComponent;
import com.zhixun.mvptest.mvp.Contract.HomeContract;
import com.zhixun.mvptest.mvp.presenter.HomePresenter;
import com.zhixun.mvptest.ui.activity.BlackActivity;
import com.zhixun.mvptest.ui.activity.DropmenuActivity;
import com.zhixun.mvptest.ui.activity.FirstActivity;
import com.zhixun.mvptest.ui.activity.ReFreshActivity;
import com.zhixun.mvptest.ui.anim.SlideInRightAnimation;
import com.zhixun.mvptest.ui.bean.Announcement;
import com.zhixun.mvptest.ui.bean.AnnouncementData;
import com.zhixun.mvptest.ui.bean.Banner;
import com.zhixun.mvptest.ui.bean.BannerData;
import com.zhixun.mvptest.ui.bean.HomeOrderData;
import com.zhixun.mvptest.ui.bean.LimitBean;
import com.zhixun.mvptest.utils.CommonUtils;
import com.zhixun.mvptest.utils.PopWinDownUtil;
import com.zhixun.mvptest.view.MarqueeTextView;
import com.zhixun.mvptest.view.MarqueeView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 投资界面
 * Created by Administrator on 2018/3/15.
 */

public class HomeFragment extends BaseRVFragment<HomePresenter> implements HomeContract.View, HomeAdapter.OnItemClickListener, BGABanner.Adapter<ImageView, String>, BGABanner.Delegate<ImageView, String> {
    @BindView(R.id.bga_banner)
    BGABanner bgaBanner;
    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.rl_msg)
    RelativeLayout rlMsg;
    @BindView(R.id.tv_tishi)
    MarqueeTextView tvTishi;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.ly_tishi)
    LinearLayout lyTishi;
    @BindView(R.id.tv_sort)
    TextView tvSort;
    @BindView(R.id.ll_sort)
    LinearLayout llSort;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;
    @BindView(R.id.ll_filter)
    LinearLayout llFilter;
    @BindView(R.id.ll_title)
    RelativeLayout llTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.iv_nomsg)
    ImageView ivNomsg;
    @BindView(R.id.tv_nomsg)
    TextView tvNomsg;
    @BindView(R.id.tv_again_load)
    TextView tvAgainLoad;
    @BindView(R.id.ll_nomessage)
    LinearLayout llNomessage;
    @BindView(R.id.ll_rlv)
    LinearLayout llRlv;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    private PopWinDownUtil popWinDownUtil1;
    private PopWinDownUtil popWinDownUtil2;
    private HomeAdapter homeAdapter;
    private List<String> sortList = new ArrayList<>();

    private int bannerType = 1;//展示端口 1,Android 2,IOS 3 h5
    private List<String> imgUrl = new ArrayList<>();
    private List<BannerData> data;
    private int applyDayStart = 0;//开始期限区间
    private int applyDayEnd = 100;//结束期限区间
    private int limit = 10;//每页限制显示多少条
    private int page = 1;//开始页码
    private Number yearRateStart = 0;
    private Number yearRateEnd = 100;
    private int sort = 0;
    private String userId;
    private String currentTime;
    private float toolBarBgAlpha;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void configViews() {
        initMsg();
        initSort();
        requestApi();
        mPresenter.queryBanner(bannerType);
        initRefresh();
        mPresenter.queryAnnouncement();
    }



    private void requestApi() {
        showDialog("加载中...");
        mPresenter.queryOrderList(applyDayStart, applyDayEnd, limit, page, sort, yearRateStart, yearRateEnd);
    }

    @Override
    public void initDatas() {
        initPopWin();
        initRecyclerview();
    }

    private void initRefresh() {
        //代码设置刷新的高度
        refreshLayout.setFooterHeight(40);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                requestApi();

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                requestApi();
                mPresenter.queryBanner(bannerType);
                //mPresenter.isExistPushInfo(userId);
            }
        });
    }
    private void initRecyclerview() {
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
//        llm.setSmoothScrollbarEnabled(true);
//        llm.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(llm);
//        recyclerView.setHasFixedSize(false);
        homeAdapter = new HomeAdapter(mContext);
        homeAdapter.setAnimation(new SlideInRightAnimation());
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickListener(this);
    }

    private void initSort() {
        sortList.add("最新发布优先");
        sortList.add("期限从短到长");
        sortList.add("期限从长到短");
        sortList.add("利率从高到底");
        sortList.add("利率从低到高");
        sortList.add("打赏金额从高到低");
        sortList.add("打赏金额从低到高");
    }

    private void initPopWin() {
        final View dropview = LayoutInflater.from(getActivity()).inflate(R.layout.dropdown_content, null);
        popWinDownUtil1 = new PopWinDownUtil(getActivity(), dropview, llTitle);
        View dropview2 = LayoutInflater.from(getActivity()).inflate(R.layout.dropdown2_content, null);
        popWinDownUtil2 = new PopWinDownUtil(getActivity(), dropview2, llTitle);
        popWinDownUtil1.setOnDismissListener(new PopWinDownUtil.OnDismissLisener() {
            @Override
            public void onDismiss() {
                tvSort.setSelected(false);
            }
        });
        popWinDownUtil2.setOnDismissListener(new PopWinDownUtil.OnDismissLisener() {
            @Override
            public void onDismiss() {
                tvShaixuan.setSelected(false);
            }
        });
        TextView tvNext = dropview2.findViewById(R.id.tv_next);
        final CheckBox cb1 = (CheckBox) dropview2.findViewById(R.id.cb1);
        final CheckBox cb2 = (CheckBox) dropview2.findViewById(R.id.cb2);
        final EditText et1 = (EditText) dropview2.findViewById(R.id.et1);
        final EditText et2 = (EditText) dropview2.findViewById(R.id.et2);
        final EditText et3 = (EditText) dropview2.findViewById(R.id.et3);
        final EditText et4 = (EditText) dropview2.findViewById(R.id.et4);
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // initData();
                if (cb1.isChecked() || cb2.isChecked()) {
                    if (cb1.isChecked()) {
                        String yearStart = et1.getText().toString();
                        String yearEnd = et2.getText().toString();
                        if (yearStart.isEmpty() || yearEnd.isEmpty()) {
                            // DialogUtil.showDialog(mContext, "请输入利率区间", "", "确定");
                            return;
                        } else {
                            yearRateStart = Double.parseDouble(yearStart);
                            yearRateEnd = Double.parseDouble(yearEnd);
                        }
                    } else {
                        yearRateStart = 0;
                        yearRateEnd = 100;
                    }
                    if (cb2.isChecked()) {
                        String applyStart = et3.getText().toString();
                        String applyEnd = et4.getText().toString();
                        if (applyStart.isEmpty() || applyEnd.isEmpty()) {
                            // DialogUtil.showDialog(mContext, "请输入期限区间", "", "确定");
                            return;
                        } else {
                            applyDayStart = Integer.parseInt(applyStart);
                            applyDayEnd = Integer.parseInt(applyEnd);
                        }
                    } else {
                        applyDayStart = 0;
                        applyDayEnd = 100;
                    }
                } else {
                    yearRateStart = 0;
                    yearRateEnd = 100;
                    applyDayStart = 0;
                    applyDayEnd = 100;
                }
                page = 1;
                requestApi();
                popWinDownUtil2.hide();
            }
        });
        ListView lv = (ListView) dropview.findViewById(R.id.sort_list);
        final SortAdapter sortAdapter = new SortAdapter(mContext, sortList);
        lv.setAdapter(sortAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                sortAdapter.changeSelected(position);
                page = 1;
                sort = position;
                requestApi();
                popWinDownUtil1.hide();
            }
        });
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    private void initMsg() {
        //设置右侧消息按钮在状态栏以下
        int statusBarHeight = CommonUtils.getStatusBarHeight(getActivity());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.topMargin = statusBarHeight - 10;
        lp.rightMargin = 20;
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rlMsg.setLayoutParams(lp);
    }

    @OnClick({R.id.ll_sort, R.id.ll_filter, R.id.rl_msg, R.id.tv_again_load, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_sort:
                // 排序
                if (tvSort.isSelected()) {
                    tvSort.setSelected(false);
                    tvShaixuan.setSelected(false);
                } else {
                    tvSort.setSelected(true);
                    tvShaixuan.setSelected(false);
                }
                if (popWinDownUtil1.isShowing()) {
                    popWinDownUtil1.hide();
                } else {
                    popWinDownUtil1.show();
                }
                if (popWinDownUtil2.isShowing()) {
                    popWinDownUtil2.hide();
                }
                break;
            case R.id.ll_filter:
                //筛选
                if (tvShaixuan.isSelected()) {
                    tvSort.setSelected(false);
                    tvShaixuan.setSelected(false);
                } else {
                    tvShaixuan.setSelected(true);
                    tvSort.setSelected(false);
                }
                if (popWinDownUtil2.isShowing()) {
                    popWinDownUtil2.hide();
                } else {
                    popWinDownUtil2.show();
                }
                if (popWinDownUtil1.isShowing()) {
                    popWinDownUtil1.hide();
                }
                break;
            case R.id.rl_msg:
                //消息中心
                //MessageActivity.startActivity(mContext);
                break;
            case R.id.tv_again_load:
                // refreshLayout.resetNoMoreData();
                page = 1;
                requestApi();
                mPresenter.queryBanner(bannerType);
                // mPresenter.isExistPushInfo(userId);
                break;
            case R.id.iv_close:
                //关闭广告位
                lyTishi.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onItemClick(String orderId) {
        //条目点击  进入详情
        ReFreshActivity.startActivity(mContext);
    }


    @Override
    public void showError(String err) {
        dismissDialog();
        refreshLayout.finishRefresh(1000, false);
        recyclerView.setVisibility(View.GONE);
        llNomessage.setVisibility(View.VISIBLE);
        tvNomsg.setText("网络错误或服务器异常！");
        tvAgainLoad.setVisibility(View.VISIBLE);
        tvAgainLoad.setText("刷新");
        refreshLayout.finishRefresh(1000, false);
        refreshLayout.finishLoadMore(false);
    }

    @Override
    public void complete() {
        dismissDialog();
    }

    @Override
    public void twoUser() {
        // showUserDialog();
    }

    @Override
    public void showqueryOrderList(HomeOrderData homeOrderData) {
        //ZToast.makeText(getActivity(),"获取数据成功",1000).show();
        refreshLayout.finishRefresh(1000, true);
        if (homeOrderData != null) {
            if (homeOrderData.getData() == null || homeOrderData.getData().size() == 0) {
                //没有数据
                if (page == 1) {
                    recyclerView.setVisibility(View.GONE);
                    llNomessage.setVisibility(View.VISIBLE);
                    ivNomsg.setImageResource(R.mipmap.no_data);
                    tvAgainLoad.setVisibility(View.GONE);
                    tvNomsg.setText("暂无借款信息");
                    refreshLayout.setEnableLoadMore(true);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                llNomessage.setVisibility(View.GONE);
                refreshLayout.finishLoadMore(1000, true,false);
                if (page == 1) {
                    if (homeOrderData.getData().size() < 10) {
                        refreshLayout.setEnableLoadMore(false);
                    } else {
                        refreshLayout.setEnableLoadMore(true);
                    }
                    homeAdapter.refreshData(homeOrderData.getData());
                } else {
                    homeAdapter.addData(homeOrderData.getData());
                }
            }
        }
    }


    @Override
    public void showreceiveCoupon(BaseBean baseBean) {
        //优惠券获取成功 将当前时间存到本地
        // SpUtils.setTime(mContext, currentTime);
    }


    @Override
    public void showqueryAppUseParam(LimitBean limitBean) {
        //保存所有限制金额的map对象
        if (limitBean != null) {
            // SpUtils.setLimitConfig(mContext, limitBean.getData());
        }
    }

    @Override
    public void showAnnouncement(Announcement announcement) {
        //公告数据获取成功
        if (announcement != null) {
            if (announcement.getData() == null || announcement.getData().size() == 0) {
                //没有公告数据  隐藏公告
                lyTishi.setVisibility(View.GONE);
            } else {
                lyTishi.setVisibility(View.VISIBLE);
                String announcementStr = "";
                List<AnnouncementData> announcementDataList = announcement.getData();
                for (int i = 0; i < announcementDataList.size(); i++) {
                    announcementStr += announcementDataList.get(i).getContent() + "   ";
                }
                tvTishi.setText(announcementStr);
            }
        }

    }

    @Override
    public void showBanner(Banner banner) {
        if (banner != null && banner.getData().size() != 0) {
            HomeCarouselAdapter adapter = new HomeCarouselAdapter(mContext);
            bgaBanner.setDelegate(this);
            bgaBanner.setAdapter(adapter);
            data = banner.getData();
            Collections.sort(data, new Comparator<BannerData>() {
                @Override
                public int compare(BannerData p1, BannerData p2) {
                    if (p1.getPriority() > p2.getPriority()) return -1;
                    else if (p1.getPriority() == p2.getPriority()) return 0;
                    else return 1;
                }
            });
            if (data.size() == 1) {
                bgaBanner.stopAutoPlay();
                bgaBanner.setAutoPlayAble(false);
            }
            if (imgUrl != null) {
                imgUrl.clear();
            }
            for (int i = 0; i < data.size(); i++) {
                imgUrl.add(data.get(i).getImgUrl());
            }
            bgaBanner.setData(imgUrl, null);
            bgaBanner.setAdapter(this);
        }

    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, final String model, final int positions) {
        Glide.with(mContext)
                .load(model)
                .placeholder(R.mipmap.no_network_icon)
                .error(R.mipmap.no_network_icon)
                .centerCrop()
                .dontAnimate()
                .into(itemView);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, @Nullable String model, int position) {
        BlackActivity.startActivity(mContext);
    }


    @Override
    public void onStart() {
        super.onStart();
        marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
    }

}
