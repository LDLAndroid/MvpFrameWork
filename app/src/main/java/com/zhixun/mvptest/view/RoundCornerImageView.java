package com.zhixun.mvptest.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.zhixun.mvptest.R;

/**
 * 圆角矩形
 * Created by Administrator on 2018/6/1.
 */

@SuppressLint("AppCompatCustomView")
public class RoundCornerImageView extends ImageView {
    private int cornerSize;//圆角大小

    public RoundCornerImageView(Context context){
        this(context,null);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView,defStyle,0);
        cornerSize = a.getInt(R.styleable.RoundCornerImageView_corner_size,5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int w = getWidth();
        int h = getHeight();
        //这里对path添加一个圆角区域，这里一般需要将dp转换为pixel
        path.addRoundRect(new RectF(0,0,w,h), DensityUtil.dp2px((float)cornerSize), DensityUtil.dp2px((float)cornerSize), Path.Direction.CW);
        canvas.clipPath(path);//将Canvas按照上面的圆角区域截取
        super.onDraw(canvas);
    }

    /**
     * 设置圆角的大小
     * @param size
     */
    public void setCornerSize(int size){
        cornerSize = size;
    }
}