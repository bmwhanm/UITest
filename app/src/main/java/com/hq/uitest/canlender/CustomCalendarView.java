package com.hq.uitest.canlender;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hq.uitest.R;

/**
 * Created by heqiang on 17/9/11.
 */

public class CustomCalendarView extends View {

    private Paint mPaint;
    private int mBgMonth,mBgWeek,mBgDay,mBgPre;
    private int mMonthRowL,mMonthRowR;
    private float mMonthRowSpac;
    private int mTextColorMonth ;

    private float mTextSizeMonth;


    public CustomCalendarView(Context context) {
        this(context, null);
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs,R.styleable.CustomCalendarView,defStyleAttr,0);

        mBgMonth = ta.getColor(R.styleable.CustomCalendarView_mBgMonth, Color.TRANSPARENT);
        mBgWeek = ta.getColor(R.styleable.CustomCalendarView_mBgWeek, Color.TRANSPARENT);
        mBgDay = ta.getColor(R.styleable.CustomCalendarView_mBgDay, Color.TRANSPARENT);
        mBgPre = ta.getColor(R.styleable.CustomCalendarView_mBgPre, Color.TRANSPARENT);

        mMonthRowL = ta.getResourceId(R.styleable.CustomCalendarView_mMonthRowL, R.drawable.custom_calendar_row_left);
        mMonthRowR = ta.getResourceId(R.styleable.CustomCalendarView_mMonthRowR, R.drawable.custom_calendar_row_right);
        mMonthRowSpac = ta.getDimension(R.styleable.CustomCalendarView_mMonthRowSpac, 20);

        mTextColorMonth = ta.getColor(R.styleable.CustomCalendarView_mTextColorMonth,Color.BLACK);
        mTextSizeMonth = ta.getDimension(R.styleable.CustomCalendarView_mTextSizeMonth,100);

        ta.recycle();

        initComputer();
    }

    private void initComputer() {
        mPaint = new Paint();
        mPaint.setTextSize(50);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap mBitmapL = BitmapFactory.decodeResource(getResources(),mMonthRowL);
        String str = "2017年09月";
        int mTextTop = getPaddingTop();
        float mTextHeight = FontUtil.getFontHeight(mPaint);
        int mBitmapY = (int) ((mTextHeight  - mBitmapL.getHeight())/ 2 + mTextTop);
        float length = mPaint.measureText(str,0,str.length());
        float startL = (getMeasuredWidth() - length) / 2 - mMonthRowSpac ;
        canvas.drawBitmap(mBitmapL,startL,mBitmapY,mPaint);
        Log.e("TAG","mBitmapY:"+mBitmapY+"   startL:"+startL+"  mMonthRowSpac:"+mMonthRowSpac);


        canvas.drawText(str,(getMeasuredWidth() - length) / 2,FontUtil.getFontLeading(mPaint)+mTextTop,mPaint);
        Bitmap mBitmapR = BitmapFactory.decodeResource(getResources(),mMonthRowR);
        float startR = (getMeasuredWidth() + length) / 2 + mMonthRowSpac;
        canvas.drawBitmap(mBitmapR,startR,mBitmapY,mPaint);
        Log.e("TAG","   startR:"+startR+"  mMonthRowSpac:"+mMonthRowSpac);



    }


    private PointF mFocusPoint = new PointF();
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mFocusPoint.set(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mFocusPoint.set(event.getX(),event.getY());

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:

                break;
        }

        return true;
    }


    private void touchFocusMove(PointF pointF,boolean eventEnd){

    }
}
