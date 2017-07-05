package com.hq.uitest.aigeselfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by heqiang on 17/6/7.
 */

public class FontView extends View {
    private Paint mPaint;
    private Paint.FontMetrics metrics;
    public FontView(Context context) {
        this(context,null);
    }

    public FontView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FontView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setTextSize(50);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);

        metrics = mPaint.getFontMetrics();

        Log.e("TAG","text Top :"+metrics.top);
        Log.e("TAG","text Ascent :"+metrics.ascent);
        Log.e("TAG","text Leading :"+metrics.leading);
        Log.e("TAG","text descent :"+metrics.descent);
        Log.e("TAG","text Bottom :"+metrics.bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setTextSize(50);
        canvas.drawText("this is Text 谁都会发光",0,Math.abs(metrics.top),mPaint);


        mPaint.setColor(Color.RED);
        mPaint.setTypeface(Typeface.SANS_SERIF);
        canvas.drawLine(0,0,getWidth(),0,mPaint);
        mPaint.setStrokeWidth(1);
        canvas.drawLine(0,metrics.ascent - metrics.top,getWidth(),metrics.ascent - metrics.top,mPaint);
        canvas.drawLine(0,metrics.descent - metrics.top,getWidth(),metrics.descent - metrics.top,mPaint);
        canvas.drawLine(0,metrics.bottom - metrics.top,getWidth(),metrics.bottom - metrics.top,mPaint);


        //画整个view的中间的那条线
        float baseX = (getWidth() / 2  - mPaint.measureText("this is Text 谁都会发光") / 2);
        float baseY = (getHeight() / 2  - (mPaint.ascent() + mPaint.descent()) / 2);
        canvas.drawLine(0,getHeight() /2 ,getWidth(),getHeight() / 2,mPaint);
        canvas.drawText("this is Text 谁都会发光",baseX,getHeight()/ 2,mPaint);
    }
}
