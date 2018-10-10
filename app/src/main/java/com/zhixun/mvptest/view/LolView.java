package com.zhixun.mvptest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.zhixun.mvptest.ui.bean.AbilityBean;
import com.zhixun.mvptest.utils.CommonUtils;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/18.
 */

public class LolView extends View {
    private AbilityBean abilityBean;//源数据 用于显示每个顶点显示的文字
    private int n;//边的个数
    private float R;//每个顶点到中心的距离
    private int intervalCount;//层数 这里为4层
    private float angle;//每两个顶点到中心点之间的角度
    private int viewWith;//控件宽度
    private int viewHeight;//控件高度
    private ArrayList<ArrayList<PointF>> pointArrayList;
    private Paint linePaint;//画线的画笔
    private Paint textPaint;//画文字的笔


    public LolView(Context context) {
        //这里使用this使得  不管怎样都会进入第三个构造函数中
        this(context, null);
    }

    public LolView(Context context, @Nullable AttributeSet attrs) {
        //这里使用this使得  不管怎样都会进入第三个构造函数中
        this(context, attrs, 0);
    }

    public LolView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化一些固定的数据
        initSize(context);
        //初始化多边形的所有点 每一圈七个点  共有四圈
        initPoint();
        //初始化画笔
        initPaint(context);


    }

    /**
     * 初始化固定数据
     *
     * @param context
     */
    private void initSize(Context context) {
        //控件的边的数量 这里是七边形
        n = 7;
        R = CommonUtils.dip2px(context, 100);
        intervalCount = 4;
        //一周是2π,由于禁止的问题这里使用π (Math.PI代表π)
        angle = (float) (2 * Math.PI / n);
        //获取屏幕的宽度
        int screenWith = getResources().getDisplayMetrics().widthPixels;
        //设置控件的宽高为屏幕的宽度
        viewWith = screenWith;
        viewHeight = screenWith;
    }

    /**
     * 初始化所有的点
     */
    private void initPoint() {
        //每一层的点数代表一个数组 然后将四层的点数放入一个数组中
        pointArrayList = new ArrayList<>();
        float x;
        float y;
        for (int i = 0; i < intervalCount; i++) {
            ArrayList<PointF> points = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                //每一圈的半径都按比例减少
                float r = R * (float) (4 - i) / intervalCount;
                //这里减去Math.PI / 2 是为了让多边形逆时针旋转90度，所以后面的所有用到cos,sin的都要减
                x = (float) (r * Math.cos(j * angle - Math.PI / 2));
                y = (float) (r * Math.sin(j * angle - Math.PI / 2));
                points.add(new PointF(x, y));
            }
            pointArrayList.add(points);
        }
    }

    /**
     * 初始化画笔
     *
     * @param context
     */
    private void initPaint(Context context) {
        //画线的笔
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置线的宽度
        linePaint.setStrokeWidth(CommonUtils.dip2px(context, 1f));

        //画文字的笔
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置文字居中显示
        textPaint.setTextAlign(Paint.Align.CENTER);
        //设置文字颜色
        textPaint.setColor(Color.BLACK);
        //设置文字大小
        textPaint.setTextSize(CommonUtils.dip2px(context, 14f));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置控件的最终大小
        setMeasuredDimension(viewWith, viewHeight);
    }

    //当控件大小发生变化时

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initSize(getContext());
    }

    /**
     * 绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //把画布的原点移动到屏幕的中心点
        canvas.translate(viewWith / 2, viewHeight / 2);
        //绘制多边形的边 每一层都绘制 共四层
        drawSide(canvas);
        //画轮廓线
        drawOutLine(canvas);
        //画最外层顶点的描述文字
        drawTextView(canvas);
    }




    private void drawSide(Canvas canvas) {
        //保存画布当前状态(平移、放缩、旋转、裁剪等),和canvas.restore()配合使用
        canvas.save();
        //设置画笔的样式为填充且描边
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        Path path = new Path();
        for (int i = 0; i < intervalCount; i++) {
            //每一层的颜色都都不同
            switch (i) {
                case 0:
                    linePaint.setColor(Color.parseColor("#D4F0F3"));
                    break;
                case 1:
                    linePaint.setColor(Color.parseColor("#99DCE2"));
                    break;
                case 2:
                    linePaint.setColor(Color.parseColor("#56C1C7"));
                    break;
                case 3:
                    linePaint.setColor(Color.parseColor("#278891"));
                    break;
            }
            for (int j = 0; j < n; j++) {
                //每一个点对应的x,y轴坐标
                float x = pointArrayList.get(i).get(j).x;
                float y = pointArrayList.get(i).get(j).y;
                //如果是每一层的第一个点就将路径的起点设置为这个点
                if (j == 0) {
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }
            path.close();//设置为闭合
            canvas.drawPath(path, linePaint);
            path.reset();//清除path存储的路径
        }
        canvas.restore();
    }

    /**
     * 画轮廓线
     * 1  先画最外层轮廓线
     * 2  顶点到中心的线
     * @param canvas
     */
    private void drawOutLine(Canvas canvas) {
        canvas.save();//保存画布当前状态(平移、放缩、旋转、裁剪等),和canvas.restore()配合使用

        linePaint.setColor(Color.parseColor("#99DCE2"));
        linePaint.setStyle(Paint.Style.STROKE); //设置空心的

        //画最外层的边线
        Path path=new Path();
        for (int i=0;i<n;i++){
            float x = pointArrayList.get(0).get(i).x;
            float y = pointArrayList.get(0).get(i).y;
            if(i==0){
                path.moveTo(x,y);
            }else{
                path.lineTo(x,y);
            }
        }
        path.close();
        canvas.drawPath(path,linePaint);


        //画顶点到中心的线
        for(int j=0;j<n;j++){
            float x = pointArrayList.get(0).get(j).x;
            float y = pointArrayList.get(0).get(j).y;
            canvas.drawLine(0,0,x,y,linePaint);
        }

        canvas.restore();
    }


    private void drawTextView(Canvas canvas) {
        canvas.save();
        //先计算出坐标来
        ArrayList<PointF> textPoints = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            float r = R + CommonUtils.dip2px(getContext(), 15f);
            float x = (float) (r * Math.cos(i * angle - Math.PI / 2));
            float y = (float) (r * Math.sin(i * angle - Math.PI / 2));
            textPoints.add(new PointF(x, y));
        }
        //拿到字体测量器
        Paint.FontMetrics metrics = textPaint.getFontMetrics();
        String[] abilitys = AbilityBean.getAbilitys();
        for (int i = 0; i < n; i++) {
            float x = textPoints.get(i).x;
            //ascent:上坡度，是文字的基线到文字的最高处的距离
            //descent:下坡度,，文字的基线到文字的最低处的距离
            float y = textPoints.get(i).y - (metrics.ascent + metrics.descent) / 2;
            canvas.drawText(abilitys[i], x, y, textPaint);
        }

        canvas.restore();
    }

}
