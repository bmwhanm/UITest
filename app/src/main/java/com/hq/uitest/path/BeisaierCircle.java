package com.hq.uitest.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.hq.uitest.R;

/**
 * Created by heqiang on 17/7/24.
 */

public class BeisaierCircle extends View {
    private Path mPath;
    private Paint mPaint;
    public BeisaierCircle(Context context) {
        this(context,null);
    }

    public BeisaierCircle(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BeisaierCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(1);
        mPath = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        mPath.moveTo(0,100);
        mPath.cubicTo(0,100 * 0.552284749831f,100 * 0.552284749831f,0,100,0);
        mPath.cubicTo(100 * 1.552284749831f,0,200 ,100 * 0.552284749831f,200,100);
        mPath.cubicTo(200 ,100 * 1.552284749831f,100 * 1.552284749831f,200,100,200);
        mPath.cubicTo(100 * 0.552284749831f,200,0,100 * 1.552284749831f,0,100);
        canvas.drawPath(mPath,mPaint);


        canvas.translate(210,0);
        mPath.reset();
        mPath.moveTo(0,100);
        mPath.cubicTo(0,100 * 0.552284749831f,100 * 0.552284749831f,0,100,0);
        mPath.cubicTo(100 * 1.552284749831f,0,200 ,100 * 0.552284749831f,200,100);
        mPath.cubicTo(200 ,100 * 1.552284749831f,100 * 1.552284749831f,200,100,200);
        mPath.cubicTo(100 * 0.552284749831f,200,0,100 * 1.552284749831f,0,100);
        canvas.drawPath(mPath,mPaint);

        canvas.translate(210,0);
        canvas.drawCircle(100,100,100,mPaint);
    }
}
