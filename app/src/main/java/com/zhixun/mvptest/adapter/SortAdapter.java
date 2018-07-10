package com.zhixun.mvptest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.zhixun.mvptest.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class SortAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<String> sortList;
    private int mSelect =0;

    public SortAdapter(Context mContext, List<String> sortList) {
        this.mContext = mContext;
        this.sortList = sortList;
    }

    @Override
    public int getCount() {
        return sortList.size();
    }

    @Override
    public Object getItem(int position) {
        return sortList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void changeSelected(int positon){ //刷新方法
        if(positon != mSelect){
            mSelect = positon;
            notifyDataSetChanged();
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHoler holer;
        if (convertView == null) {
            holer = new ViewHoler();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sort, null);
            holer.tvItem = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(holer);
        } else {
            holer = (ViewHoler) convertView.getTag();
        }
        holer.tvItem.setText(sortList.get(position));
        if(mSelect==position){
            holer.tvItem.setTextColor(Color.parseColor("#f95e49")); //选中项背景
        }else{
            holer.tvItem.setTextColor(Color.parseColor("#333333"));  //其它项背景
        }
        return convertView;
    }

    static class ViewHoler {
        TextView tvItem;
    }
}
