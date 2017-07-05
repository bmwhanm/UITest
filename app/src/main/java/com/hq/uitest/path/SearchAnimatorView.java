package com.hq.uitest.path;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by heqiang on 17/6/16.
 */

public class SearchAnimatorView extends View {
    private Paint mPaint;
    private static final int RADIUS = 70;
    private RectF mRect;
    private float fraction;
    private Path mPath;
    private Path mBottomPath;
    private final String START_ANIMATION = "START_ANIMATION";
    private final String RESET_ANIMATION = "RESET_ANIMATION";
    private String tag ;
    private ValueAnimator animator;


    public SearchAnimatorView(Context context) {
        this(context, null);
    }

    public SearchAnimatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchAnimatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPath = new Path();
        mBottomPath = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GREEN);
        mPath.reset();

//        canvas.drawCircle(getWidth()/2,getHeight()/2,RADIUS,mPaint);
//        canvas.save();
//        canvas.rotate(45, getWidth() / 2, getHeight() / 2);
//        mPath.moveTo(getWidth() / 2 + RADIUS, getHeight() / 2);
//        mPath.lineTo(RADIUS * 3 + getWidth() / 2, getHeight() / 2);
//        canvas.drawPath(mPath, mPaint);
//        canvas.restore();
//        canvas.drawLine(0,getHeight() / 2 + 2.1f * RADIUS,800,getHeight() /2 + 2.1f * RADIUS,mPaint);


        //执行开始的动画
        if(tag != null && tag.equals(START_ANIMATION)) {
            //圆要慢慢消失，下面的直线慢慢出来
            if (fraction >= 0 && fraction <= 0.5f) {
                float fr = fraction * 2;//0-1
                float degress = fr * 360;
                canvas.drawArc(mRect, 45, degress - 360, false, mPaint);
                canvas.save();
                canvas.rotate(45, getWidth() / 2, getHeight() / 2);
                mPath.moveTo(getWidth() / 2 + RADIUS, getHeight() / 2);
                mPath.lineTo(RADIUS * 3 + getWidth() / 2, getHeight() / 2);
                canvas.drawPath(mPath, mPaint);
                canvas.restore();
                //随着画布的平移画最下面的那条线
                drawBottomPath(canvas);
            } else if (fraction > 0.5f && fraction <= 1f) {
                float fr = fraction * 2 - 1;//0 - 1
                canvas.save();
                canvas.translate(1.9f*RADIUS * fr, 0);
                canvas.save();
                canvas.rotate(45, getWidth() / 2, getHeight() / 2);
//                canvas.drawColor(Color.GREEN);
                mPath.reset();
                mPath.moveTo(getWidth() / 2 + RADIUS + 2 * RADIUS * fr, getHeight() / 2);
                mPath.lineTo(RADIUS * 3 + getWidth() / 2, getHeight() / 2);
                canvas.drawPath(mPath, mPaint);
                //画布恢复
                canvas.restore();

                //随着画布的平移画最下面的那条线
                drawBottomPath(canvas);

                //画布恢复
                canvas.restore();

            }
        }

        //执行Reset动画
        if (tag != null && tag.equals(RESET_ANIMATION)){
            //圆要慢慢消失，下面的直线慢慢出来
            if (fraction > 0.5f && fraction <= 1f) {
                float fr = fraction * 2 - 1;//0f-1f
                float degress = fr * 360;
                canvas.drawArc(mRect, 45, degress , false, mPaint);
                canvas.save();
                canvas.rotate(45, getWidth() / 2, getHeight() / 2);
                mPath.reset();
                mPath.moveTo(getWidth() / 2 + RADIUS, getHeight() / 2);
                mPath.lineTo(RADIUS * 3 + getWidth() / 2, getHeight() / 2);
                canvas.drawPath(mPath, mPaint);
                canvas.restore();
                //随着画布的平移画最下面的那条线
                drawBottomPath(canvas);
            } else if (fraction >= 0f && fraction <= 0.5f) {
                float fr = fraction * 2;//0 - 1
                canvas.save();
                canvas.translate(1.9f * RADIUS * (1-fr), 0);
                canvas.save();
                canvas.rotate(45, getWidth() / 2, getHeight() / 2);
//                canvas.drawColor(Color.GREEN);
                mPath.reset();
                mPath.moveTo(RADIUS * 3 + getWidth() / 2, getHeight() / 2);
                mPath.lineTo(getWidth() / 2 + RADIUS * 3 - 2 * RADIUS * fr, getHeight() / 2);
                canvas.drawPath(mPath, mPaint);
                //画布恢复
                canvas.restore();

                //随着画布的平移画最下面的那条线
                drawBottomPath(canvas);
                //画布恢复
                canvas.restore();
            }
        }

    }


    //随着画布的平移画最下面的那条线
    public void drawBottomPath(Canvas canvas) {
        if(tag != null && tag.equals(START_ANIMATION)) {
            mBottomPath.reset();
            mBottomPath.moveTo(getWidth() / 2 + 2.1f * RADIUS, getHeight() / 2 + 2.1f * RADIUS);
            mBottomPath.lineTo(getWidth() / 2 + 2.1f * RADIUS - 8 * RADIUS * fraction, getHeight() / 2 + 2.1f * RADIUS);
            canvas.drawPath(mBottomPath, mPaint);
        }
        if(tag != null && tag.equals(RESET_ANIMATION)){
            mBottomPath.reset();
            mBottomPath.moveTo(getWidth() / 2 + 2.1f * RADIUS, getHeight() / 2 + 2.1f * RADIUS);
            mBottomPath.lineTo(getWidth() / 2 + 2.1f * RADIUS - 8 * RADIUS * (1-fraction), getHeight() / 2 + 2.1f * RADIUS);
            canvas.drawPath(mBottomPath, mPaint);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mRect = new RectF(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);

    }

//    private boolean first = false;
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (!first) {
//            startAnimator();
//            first = true;
//        }
//        return super.onTouchEvent(event);
//    }



    private void startAnimator() {
        if( animator != null && animator.isRunning()){
            return;
        }
        animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(5000);
        fraction = 0;
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fraction = animation.getAnimatedFraction();
                postInvalidate();
            }
        });
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }


    public void startAnim(){
        tag = START_ANIMATION;
        startAnimator();

    }
    public void resetAnim(){
        tag = RESET_ANIMATION;
        startAnimator();
    }
}
