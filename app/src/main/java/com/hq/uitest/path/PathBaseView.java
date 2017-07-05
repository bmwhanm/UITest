package com.hq.uitest.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Debug;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by heqiang on 17/6/15.
 */

public class PathBaseView extends View {
    private Paint mPaint;

    private Path mPath;
    private RectF mRect;

    public PathBaseView(Context context) {
        this(context,null);
    }

    public PathBaseView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    private void initPaint() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.RED);
        mRect = new RectF(0,0,200,200);
        mPath = new Path();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        //测试Path的基础用法
//        testPathBase(canvas);

        //测试贝赛尔曲线
        testBeisaier(canvas);
    }

    //测试贝赛尔曲线
    private void testBeisaier(Canvas canvas) {
        mPath.reset();
        //二阶贝赛尔
//        mPath.moveTo(100,100);
//        mPath.quadTo(150,50,200,100);
//        mPath.quadTo(250,150,300,100);
        // 二阶贝赛尔选择相对的函数
//        mPath.moveTo(100,100);
//        mPath.rQuadTo(50,-50,100,0);
//        mPath.rQuadTo(50,50,100,0);


        //三阶贝赛尔
//        mPath.moveTo(100,100);
//        mPath.cubicTo(150,50,250,150,300,100);

        //三阶贝赛尔选择相对的函数
        mPath.moveTo(100,100);
        mPath.rCubicTo(50,-50,150,50,200,0);

        canvas.drawPath(mPath,mPaint);
    }

    //测试Path的基础用法
    private void testPathBase(Canvas canvas) {
        mPath.reset();
        //画矩形
//        mPath.addRect(mRect, Path.Direction.CCW);

        //画圆弧
//        mPath.addArc(mRect,0,45);

        //画条线
//        mPath.moveTo(100,100);
//        mPath.lineTo(300,300);
//        mPath.moveTo(100,200);
//        mPath.lineTo(300,400);
//        mPath.lineTo(200,100);
//        mPath.close();

        //画圆角矩形
//        mPath.addRoundRect(mRect,10,10, Path.Direction.CCW);

        //画圆
//        mPath.addCircle(100,100,50, Path.Direction.CW);


        //画椭圆
//        mPath.addOval(0f,0f ,300f ,400f, Path.Direction.CCW);


        //画两条线
//        mPath.addRect(mRect, Path.Direction.CCW);
//        mPath.addRect(50,50,150,150, Path.Direction.CCW);

        canvas.drawPath(mPath,mPaint);
    }
}
