package com.zhixun.mvptest.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zhixun.mvptest.R;
import com.zhixun.mvptest.utils.CommonUtils;

/**
 * Created by Administrator on 2018/7/31.
 */

public class HeartView extends FrameLayout {
    private int viewWith = CommonUtils.dip2px(getContext(), 26), viewHeight = CommonUtils.dip2px(getContext(), 26);
private int [] resImg={R.mipmap.heart,R.mipmap.heart1,R.mipmap.heart2,R.mipmap.heart3,R.mipmap.heart4,R.mipmap.heart5,R.mipmap.heart6,R.mipmap.heart7,R.mipmap.heart8};
    public HeartView(@NonNull Context context) {
        super(context);
    }

    public HeartView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeartView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void startAnimator() {
        for(int i=0;i<20;i++){
            ImageView heartImg = new ImageView(getContext());
            heartImg.setImageResource(resImg[(int)(Math.random()*resImg.length)]);
        addView(heartImg, new RelativeLayout.LayoutParams(viewWith, viewHeight));
        //心消失的动画 透明度变化
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(heartImg, "alpha", 1.0f, 0.0f);
        //x轴偏移量
        BesselEvaluator besselEvaluator = new BesselEvaluator((float) (getWidth() * Math.random()), (float) (getWidth() * Math.random()));
        ObjectAnimator translationX = ObjectAnimator.ofObject(heartImg, "translationX", besselEvaluator, getWidth() / 2f, (float) (getWidth()* Math.random())); //x轴偏移动画
        //translationX.setInterpolator(new BounceInterpolator());
        //为了让x轴的小心心 不规则运动 添加一个插值器
        //y轴偏移量
        ObjectAnimator translationY = ObjectAnimator.ofFloat(heartImg, "translationY", getHeight(), 0);
        //定义动画集合  三个动画同时播放
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translationX).with(translationY).with(alphaAnim);
        //设置动画播放时长
        animatorSet.setDuration(10000);
        animatorSet.start();
        }
    }

    //贝塞尔曲线
    public class BesselEvaluator implements TypeEvaluator<Float> {

        private Float secondX;
        private Float thirdX;

        public BesselEvaluator(Float sencodX, Float thirdX) {
            this.secondX = sencodX;
            this.thirdX = thirdX;
        }

        @Override
        public Float evaluate(float fraction, Float start, Float end) {
            return Float.valueOf(start * (1 - fraction)
                    * (1 - fraction) * (1 - fraction)
                    + secondX * 3 * fraction * (1 - fraction)
                    * (1 - fraction) + thirdX * 3 * (1 - fraction)
                    * fraction * fraction + end * fraction * fraction * fraction);
        }


    }
}
