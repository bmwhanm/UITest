package com.hq.uitest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.util.AttributeSet;

import com.hq.uitest.util.ScreenUtil;

/**
 * @author heqiang
 * @Date 18/1/31
 */

public class UnderLineBgTextView extends AppCompatTextView {
    private Paint mPaint;
    private Context mContext;
    private Rect mRect;

    public UnderLineBgTextView(Context context) {
        this(context,null);
    }

    public UnderLineBgTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public UnderLineBgTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initPaint();

    }

    private void initPaint() {
        mPaint = new Paint();
        mRect = new Rect();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.parseColor("#eeeeee"));
        mPaint.setTextSize(ScreenUtil.dp2px(mContext, 10));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //得到TextView显示有多少行
        int count = getLineCount();

        //得到TextView的布局
        final Layout layout = getLayout();

        float x_start, x_stop, x_diff;
        int firstCharInLine, lastCharInLine;

        for (int i = 0; i < count; i++) {

            // 这个字符的顶部Y坐标就是rect的top 底部Y坐标就是rect的bottom
            firstCharInLine = layout.getLineStart(i);
            lastCharInLine = layout.getLineEnd(i);

            //要得到这个字符的左边X坐标 用layout.getPrimaryHorizontal
            //得到字符的右边X坐标用layout.getSecondaryHorizontal
            x_start = layout.getPrimaryHorizontal(firstCharInLine);
            x_diff = layout.getPrimaryHorizontal(firstCharInLine + 1) - x_start;
            x_stop = layout.getPrimaryHorizontal(lastCharInLine - 1) + x_diff;
//            x_stop = layout.getSecondaryHorizontal(lastCharInLine -1);
            getLineBounds(i, mRect);
            int bottom = mRect.bottom;
            mRect.top = bottom - ScreenUtil.dp2px(mContext, 9);
            mRect.left = (int) x_start;
            mRect.right = (int) x_stop;
            mRect.bottom = bottom - ScreenUtil.dp2px(mContext, 2);
            canvas.drawRect(mRect, mPaint);
        }

        super.onDraw(canvas);
    }
}
