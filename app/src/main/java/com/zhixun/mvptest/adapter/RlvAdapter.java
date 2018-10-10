package com.zhixun.mvptest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhixun.mvptest.R;
import com.zhixun.mvptest.ui.bean.RlvList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/7.
 */

public class RlvAdapter extends RecyclerView.Adapter<RlvAdapter.RlvHolder> {
    private final Context context;
    private final List<RlvList> dataList;



    public RlvAdapter(Context context, List<RlvList> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public RlvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rlv_item, parent, false);
        return new RlvHolder(view);
    }

    @Override
    public void onBindViewHolder(RlvHolder holder, int position) {
        RlvList rlvList = dataList.get(position);
        holder.tvDayCount.setText(rlvList.getDayCount());
        holder.tvSellerMoney.setText(rlvList.getSellerMoney());
        if (position ==0){
            holder.tvDayCount.setBackgroundResource(R.color.c_f7f7f7);
            holder.tvSellerMoney.setBackgroundResource(R.color.c_f7f7f7);
            SpannableString ss = new SpannableString(rlvList.getDayCount());
            ss.setSpan(new ForegroundColorSpan(Color.parseColor("#f95e49")), 2,rlvList.getDayCount().length() - 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.tvDayCount.setText(ss);
            holder.tvDayCount.getPaint().setFakeBoldText(true);
            holder.tvSellerMoney.getPaint().setFakeBoldText(true);
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class RlvHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_day_count)
        TextView tvDayCount;
        @BindView(R.id.tv_seller_money)
        TextView tvSellerMoney;
        @BindView(R.id.ll_item)
        LinearLayout llItem;
        public RlvHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
