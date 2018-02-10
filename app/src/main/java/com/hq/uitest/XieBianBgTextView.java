package com.hq.uitest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.hq.uitest.util.ScreenUtil;

/**
 * @author heqiang
 * @Date 18/1/31
 */

public class XieBianBgTextView extends AppCompatTextView {
    private Paint mPaint;
    private Context mContext;
    private Path mPath;
    private int bgColor = Color.WHITE;
    private int bgDirection = 1;

    public XieBianBgTextView(Context context) {
        this(context,null);
        mContext = context;
        initPaint();
    }

    public XieBianBgTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        mContext = context;
        initPaint();
    }

    public XieBianBgTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initPaint();

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XieBianBgTextView);
        bgColor = ta.getColor(R.styleable.XieBianBgTextView_bgColor,Color.WHITE);
        bgDirection = ta.getInt(R.styleable.XieBianBgTextView_bgDirection,1);
        ta.recycle();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPath = new Path();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setColor(bgColor);
        mPaint.setTextSize(ScreenUtil.dp2px(mContext, 10));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.reset();
        if(bgDirection == 1) {
            mPath.moveTo(0, 0);
            mPath.lineTo(getMeasuredWidth(), 0);
            mPath.lineTo(getMeasuredWidth() - ScreenUtil.dp2px(mContext, 8), getMeasuredHeight());
            mPath.lineTo(0, getMeasuredHeight());
            mPath.close();
        }else if(bgDirection == 2){
            mPath.moveTo(ScreenUtil.dp2px(mContext, 8),0);
            mPath.lineTo(getMeasuredWidth(),0);
            mPath.lineTo(getMeasuredWidth(),getMeasuredHeight());
            mPath.lineTo(0,getMeasuredHeight());
        }
        canvas.drawPath(mPath, mPaint);
        super.onDraw(canvas);

    }
}
