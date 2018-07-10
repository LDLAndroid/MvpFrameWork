package com.zhixun.mvptest.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhixun.mvptest.R;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 作者：${User}
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：
 * 类描述：
 * 修改时间：${DATA}1702
 */
public class HomeCarouselAdapter implements BGABanner.Adapter<ImageView, String> {
    private  Context context;

    public HomeCarouselAdapter(Context context) {
        this.context=context;
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        itemView.setImageURI(Uri.parse(model));
        Glide.with(context)
                .load(model)
                .placeholder(R.mipmap.no_network_icon)
                .error(R.mipmap.no_network_icon)
                .centerCrop()
                .dontAnimate()
                .into(itemView);
    }
}
