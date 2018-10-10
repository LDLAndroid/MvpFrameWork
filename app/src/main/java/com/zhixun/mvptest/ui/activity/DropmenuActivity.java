package com.zhixun.mvptest.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ExpandableListView;

import com.mic.bottomsheetlib.BottomSheetSettingsBuilder;
import com.mic.bottomsheetlib.interfaces.BottomSheetDialogInterface;
import com.mic.bottomsheetlib.utils.BottomSheetTitleSetting;
import com.zhixun.mvptest.R;
import com.zhixun.mvptest.adapter.ExpandAdapter;
import com.zhixun.mvptest.adapter.RlvAdapter;
import com.zhixun.mvptest.base.BaseActivity;
import com.zhixun.mvptest.component.AppComponent;
import com.zhixun.mvptest.ui.bean.ChildBean;
import com.zhixun.mvptest.ui.bean.GroupBean;
import com.zhixun.mvptest.ui.bean.RlvList;
import com.zhixun.mvptest.ui.fragment.BottomFrament.Bottom1Fragment;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/24.
 */

public class DropmenuActivity extends BaseActivity {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.elv)
    ExpandableListView elv;
    private BottomSheetDialogInterface builder;
    private List<RlvList> dataList = new ArrayList<>();
    //组数据
    private List<GroupBean> groupList = new ArrayList<>();
    //展开列表数据
    private List<List<ChildBean>> childList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_drop_menu;
    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void innitView() {
        addData();
        rlv.setLayoutManager(new LinearLayoutManager(this));
        rlv.setAdapter(new RlvAdapter(this, dataList));

        elv.setAdapter(new ExpandAdapter(this, groupList, childList));
        elv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0, count = elv
                        .getExpandableListAdapter().getGroupCount(); i < count; i++) {
                    if (groupPosition != i) {// 关闭其他分组
                        elv.collapseGroup(i);
                    }
                }
            }
        });
    }

    private void addData() {
        RlvList rlvList1 = new RlvList();
        rlvList1.setDayCount("截止实际还款日逾期天数");
        rlvList1.setSellerMoney("催收手续费");
        RlvList rlvList2 = new RlvList();
        rlvList2.setDayCount("1-5天");
        rlvList2.setSellerMoney("还款金额*20%");
        RlvList rlvList3 = new RlvList();
        rlvList3.setDayCount("6-10天");
        rlvList3.setSellerMoney("还款金额*30%");
        RlvList rlvList4 = new RlvList();
        rlvList4.setDayCount("11天以上");
        rlvList4.setSellerMoney("还款金额*40%");
        RlvList rlvList5 = new RlvList();
        rlvList5.setDayCount("11天以上");
        rlvList5.setSellerMoney("还款金额*40%");
        RlvList rlvList6 = new RlvList();
        rlvList6.setDayCount("11天以上");
        rlvList6.setSellerMoney("还款金额*40%");
        RlvList rlvList7 = new RlvList();
        rlvList7.setDayCount("11天以上");
        rlvList7.setSellerMoney("还款金额*40%");
        RlvList rlvList8 = new RlvList();
        rlvList8.setDayCount("11天以上");
        rlvList8.setSellerMoney("还款金额*40%");
        dataList.add(rlvList1);
        dataList.add(rlvList2);
        dataList.add(rlvList3);
        dataList.add(rlvList4);
        dataList.add(rlvList5);
        dataList.add(rlvList6);
        dataList.add(rlvList7);
        dataList.add(rlvList8);
        groupList.add(new GroupBean("服装"));
        groupList.add(new GroupBean("水果"));
        groupList.add(new GroupBean("零食"));
        groupList.add(new GroupBean("玩具"));

        List<ChildBean> child1 = new ArrayList<>();
        child1.add(new ChildBean("短袖"));
        child1.add(new ChildBean("卫衣"));
        child1.add(new ChildBean("衬衫"));
        child1.add(new ChildBean("短裤"));
        child1.add(new ChildBean("长裤"));
        childList.add(child1);

        List<ChildBean> child2 = new ArrayList<>();
        child2.add(new ChildBean("苹果"));
        child2.add(new ChildBean("柚子"));
        child2.add(new ChildBean("西瓜"));
        child2.add(new ChildBean("香蕉"));
        child2.add(new ChildBean("菠萝"));
        child2.add(new ChildBean("葡萄"));
        childList.add(child2);

        List<ChildBean> child3 = new ArrayList<>();
        child3.add(new ChildBean("饼干"));
        child3.add(new ChildBean("豆干"));
        child3.add(new ChildBean("饮料"));
        child3.add(new ChildBean("辣条"));
        childList.add(child3);

        List<ChildBean> child4 = new ArrayList<>();
        child4.add(new ChildBean("汽车"));
        child4.add(new ChildBean("遥控飞机"));
        child4.add(new ChildBean("溜溜球"));
        child4.add(new ChildBean("滑滑梯"));
        child4.add(new ChildBean("轮滑鞋"));
        child4.add(new ChildBean("篮球"));
        child4.add(new ChildBean("足球"));
        child4.add(new ChildBean("足球"));
        child4.add(new ChildBean("足球"));
        child4.add(new ChildBean("足球"));
        child4.add(new ChildBean("足球"));
        child4.add(new ChildBean("足球"));
        child4.add(new ChildBean("足球"));


        childList.add(child4);

    }

    @Override
    protected void innitData() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    public static void startActivity(Context context) {
        Intent i = new Intent(context, DropmenuActivity.class);
        context.startActivity(i);
    }


    @OnClick(R.id.btn_pay)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pay:
                showBottomSheet();
                break;
        }
    }

    private void showBottomSheet() {
        builder = new BottomSheetSettingsBuilder(this)
                .setOpenWindowShrinkAnim(true)
                .addOnShowListeners(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                    }
                })
                .setContainerHeight(0.5f)
                .build();
        builder.push(new Bottom1Fragment(builder), new BottomSheetTitleSetting().setTitleVisible(false));
        builder.show();
    }

}
