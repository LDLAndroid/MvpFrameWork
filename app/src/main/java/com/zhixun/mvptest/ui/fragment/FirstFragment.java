package com.zhixun.mvptest.ui.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.mic.bottomsheetlib.interfaces.BottomSheetDialogInterface;
import com.mic.bottomsheetlib.utils.BottomSheetTitleSetting;
import com.zhixun.mvptest.R;
import com.zhixun.mvptest.adapter.QuickAdapter;
import com.zhixun.mvptest.base.BaseFragment;
import com.zhixun.mvptest.component.AppComponent;
import com.zhixun.mvptest.ui.activity.ProductDetailsActivity;
import com.zhixun.mvptest.view.ZToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/6/25.
 */

@SuppressLint("ValidFragment")
public class FirstFragment extends BaseFragment {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    private List<String> dataList = new ArrayList<>();
    private QuickAdapter quickAdapter;


    public static FirstFragment newInstance() {
        Bundle args = new Bundle();
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void attachView() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_first;
    }

    @Override
    public void configViews() {
        for (int i = 0; i <35; i++) {
            dataList.add("万能recyclerview条目就大部分时间金风科技东方今典京东方积分继父回家发挥第三方很舒服还打算放松放松" + i);
        }
        quickAdapter = new QuickAdapter(R.layout.item_layout, dataList);
        quickAdapter.setEnableLoadMore(true);
        rlv.setLayoutManager(new LinearLayoutManager(mContext));
        rlv.setAdapter(quickAdapter);
        quickAdapter.openLoadAnimation(QuickAdapter.SCALEIN);
//        quickAdapter.openLoadAnimation(new BaseAnimation() {
//            @Override
//            public Animator[] getAnimators(View view) {
//                return new Animator[]{
//                        ObjectAnimator.ofFloat(view, "translationX", view.getRootView().getWidth(), 0)
//                };
//            }
//        });
        quickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ZToast.makeText(getActivity(), "条目点击==" + dataList.get(position), 1000).show();
                ProductDetailsActivity.startActivity(mContext);

            }
        });
        quickAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                ZToast.makeText(getActivity(), "条目长按", 1000).show();
                return false;
            }
        });

        quickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                ZToast.makeText(getActivity(), "加载更多", 1000).show();
                //addData();


            }
        });

    }

    private void addData() {
        for (int i=0;i<5;i++){
            dataList.add("新添加的数据"+i);
        }
        //quickAdapter.addData(dataList);
        quickAdapter.loadMoreComplete();
    }

    @Override
    public void initDatas() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

}
