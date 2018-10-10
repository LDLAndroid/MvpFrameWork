package com.zhixun.mvptest.adapter;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.animation.AlphaInAnimation;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.zhixun.mvptest.R;
import com.zhixun.mvptest.ui.bean.HomeOrderDataList;
import com.zhixun.mvptest.utils.FormatUtils;
import com.zhixun.mvptest.view.RoundCornerImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/22.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyHolder> {



    private Context mContext;
    private List<HomeOrderDataList> dataList = new ArrayList<>();

    private int mLastPosition = -1;
    private Interpolator mInterpolator = new LinearInterpolator();
    private int mDuration = 300;
    private BaseAnimation mSelectAnimation = new AlphaInAnimation();
    private boolean mOpenAnimationEnable = true;

    public HomeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        if (dataList != null) {
            final HomeOrderDataList homeOrderDataList = dataList.get(position);
            if (homeOrderDataList != null) {
                holder.tvName.setText(homeOrderDataList.getBorrowerUserName() + "");
                holder.tvShouyi.setText(FormatUtils.formatStr(homeOrderDataList.getYearRate()));
                holder.tvDashang.setText("打赏了" + FormatUtils.formatStr(homeOrderDataList.getRewardAmount()) + "元");
                holder.tvDay.setText(homeOrderDataList.getApplyDays() + "");
                holder.tvMoney.setText(FormatUtils.formatStr(homeOrderDataList.getBorrowingAmount()));
                String headPortrait = homeOrderDataList.getHeadPortrait();
                holder.tvDashang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        QMUIPopup qmuiPopup = new QMUIPopup(mContext, QMUIPopup.DIRECTION_TOP);
                        TextView textView = new TextView(mContext);
                        textView.setLayoutParams(qmuiPopup.generateLayoutParam(
                                QMUIDisplayHelper.dp2px(mContext, 250),
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        ));
                        textView.setLineSpacing(QMUIDisplayHelper.dp2px(mContext, 4), 1.0f);
                        int padding = QMUIDisplayHelper.dp2px(mContext, 10);
                        textView.setPadding(padding, padding, padding, padding);
                        textView.setText("Popup 可以设置其位置以及显示和隐藏的动画");
                        textView.setTextColor(ContextCompat.getColor(mContext, R.color.c_333333));
                        qmuiPopup.setContentView(textView);//设置在QMUIPopup上面显示的内容或控件
                        qmuiPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);//设置显示样式
                        qmuiPopup.setPreferredDirection(QMUIPopup.ANIM_GROW_FROM_LEFT);//设置显示位置
                        qmuiPopup.show(holder.tvDashang);//设置在哪个控件上显示QMUIpopup
                    }
                });
                if (headPortrait == null || headPortrait.isEmpty()) {
                    holder.ivAvatar.setImageResource(R.mipmap.touxiang);
                } else {
                    Glide.with(mContext)
                            .load(headPortrait)
                            .into(holder.ivAvatar);
                }
                holder.rlItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(homeOrderDataList.getId());
                        }
                    }
                });
            }
        }

    }


    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public void refreshData(List<HomeOrderDataList> data) {
        if (dataList != null) {
            dataList.clear();
            dataList.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addData(List<HomeOrderDataList> data) {
        if (data != null) {
            dataList.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 添加动画
     * @param holder
     */
    public void addAnimation(RecyclerView.ViewHolder holder) {
        if (mOpenAnimationEnable) {
            if (holder.getLayoutPosition() > mLastPosition) {
                BaseAnimation animation = null;
                if (mSelectAnimation != null) {
                    animation = mSelectAnimation;
                }
                for (Animator anim : animation.getAnimators(holder.itemView)) {
                    startAnim(anim);

                }
                mLastPosition = holder.getLayoutPosition();
            }
        }
    }

    /**
     * 开启动画
     * @param animator
     */
    private void startAnim(Animator animator) {
        animator.setDuration(mDuration).start();
        animator.setInterpolator(mInterpolator);
    }

    /**
     * 设置动画效果
     * @param animation
     */
    public void setAnimation(BaseAnimation animation){
        this.mOpenAnimationEnable = true;
        this.mSelectAnimation = animation;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_avatar)
        RoundCornerImageView ivAvatar;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_dashang)
        TextView tvDashang;
        @BindView(R.id.tv_shouyi)
        TextView tvShouyi;
        @BindView(R.id.tv_day)
        TextView tvDay;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;
        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //条目点击事件监听
    public interface OnItemClickListener {
        void onItemClick(String orderId);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
