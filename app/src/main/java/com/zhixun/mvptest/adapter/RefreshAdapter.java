package com.zhixun.mvptest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhixun.mvptest.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/25.
 */

public class RefreshAdapter extends RecyclerView.Adapter<RefreshAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<String> dataList;

    public RefreshAdapter(Context mContext, List<String> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvItem.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item)
        TextView tvItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
