package com.hq.uitest.aigeselfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by heqiang on 17/6/7.
 */

public class FirstView extends View implements Runnable {
    private Paint mPaint;
    private int mRadius;


    public FirstView(Context context) {
        this(context,null);
    }

    public FirstView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FirstView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.YELLOW);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2,getHeight() / 2 ,mRadius,mPaint);
    }

    @Override
    public void run() {
        while (true) {
            if (mRadius < 200) {
                mRadius += 10;
                postInvalidate();
            } else {
                mRadius = 0;
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
