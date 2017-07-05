package com.hq.uitest.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by heqiang on 17/6/14.
 */

public class CanvasBaseView extends View {
    private Paint mPaint;
    private RectF mRectF;



    public CanvasBaseView(Context context) {
        this(context,null);
    }

    public CanvasBaseView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mRectF = new RectF(0,0,300,400);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //测试Canvas的平移变换
//        testCanvasTranslate(canvas);

        //测试Canvas的缩放变换
//        testCanvasScale(canvas);


        //测试Canvas的旋转变换
        testCanvasRotate(canvas);


        //测试Canvas的斜变换
//        testCanvasSkew(canvas);


        //测试裁剪画布
//        testCanvasClip(canvas);




    }
    //测试裁剪画布
    private void testCanvasClip(Canvas canvas) {
        canvas.save();
        canvas.clipRect(0,0,800,800);
        canvas.drawColor(Color.BLUE);
        canvas.clipRect(100,100,600,600);
        mPaint.setColor(Color.RED);
        canvas.drawRect(100,100,200,200,mPaint);
        canvas.clipRect(200,200,400,400);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(200,200,300,300,mPaint);
        canvas.restore();

    }

    private void testCanvasSkew(Canvas canvas) {
        canvas.save();
        mPaint.setColor(Color.RED);
        canvas.drawRect(mRectF,mPaint);
        canvas.skew(4,5);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(mRectF,mPaint);
        canvas.restore();
    }

    //测试Canvas的旋转变换
    private void testCanvasRotate(Canvas canvas) {
        canvas.save();
        mPaint.setColor(Color.RED);
        canvas.drawRect(mRectF,mPaint);
        canvas.rotate(90,300,400);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(mRectF,mPaint);
        canvas.restore();
    }

    //测试Canvas的缩放变换
    private void testCanvasScale(Canvas canvas) {
        mPaint.setColor(Color.RED);
        canvas.drawRect(mRectF,mPaint);
        canvas.scale(1.5f,0.5f);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(mRectF,mPaint);
    }

    /**
     * canvas.save()就是把这份图层的具体的形状记住在一个栈中，以后可以调用canvas.restore()
     * 恢复这个图层，一个restore()对应着一个save()
     * @param canvas
     */
    private void testCanvasTranslate(Canvas canvas) {
        canvas.save();
        mPaint.setColor(Color.RED);
        canvas.drawRect(mRectF,mPaint);
        canvas.translate(100,100);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(mRectF,mPaint);
        canvas.restore();
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(mRectF,mPaint);
    }
}
