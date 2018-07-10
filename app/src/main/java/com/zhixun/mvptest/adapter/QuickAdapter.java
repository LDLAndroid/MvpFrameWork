package com.zhixun.mvptest.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhixun.mvptest.R;

import java.util.List;

/**
 * Created by Administrator on 2018/7/5.
 */

public class QuickAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public QuickAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item, item);
    }
}
