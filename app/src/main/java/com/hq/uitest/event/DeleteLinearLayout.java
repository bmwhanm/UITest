package com.hq.uitest.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by heqiang on 17/7/18.
 */

public class DeleteLinearLayout extends LinearLayout {
    private int mTouchSlop;
    private Scroller mScroller;
    private int MAXWIDTH;
    private int mLastX;


    public DeleteLinearLayout(Context context) {
        this(context, null);
    }

    public DeleteLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DeleteLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        setOrientation(HORIZONTAL);
        MAXWIDTH = dipToPx(context, 90);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new Scroller(context);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        boolean intercepted = false;
        int x = (int) ev.getX();
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();
            return true;
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) ev.getX();
                intercepted = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(x - mLastX) > mTouchSlop) intercepted = true;
                else intercepted = false;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                intercepted = false;

                break;
        }
        mLastX = x;
        return intercepted;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        getParent().requestDisallowInterceptTouchEvent(false);
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();
            return true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (event.getX() - mLastX);
                mLastX = (int) event.getX();
                scrollBy(-dx, 0);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                int scrollX = getScrollX();
                if (scrollX > MAXWIDTH / 3) {
                    mScroller.startScroll(scrollX, 0, MAXWIDTH - scrollX, 0, 2000);
                } else {
                    mScroller.startScroll(scrollX, 0, -scrollX, 0, 2000);
                }
                invalidate();
                break;
        }
        mLastX = (int) event.getX();
        return true;
    }


    @Override
    public void scrollTo(int x, int y) {
        if (x < 0) x = 0;
        if (x > MAXWIDTH) x = MAXWIDTH;
        super.scrollTo(x, y);

    }

    public void openLeft() {
        scrollTo(MAXWIDTH, 0);
    }


    public void closeLeft() {
        scrollTo(0, 0);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    private int dipToPx(Context context, int dip) {
        return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
