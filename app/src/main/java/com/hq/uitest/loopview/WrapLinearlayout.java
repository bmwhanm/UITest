package com.hq.uitest.loopview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.hq.uitest.viewpager.verticalvp.VerticalViewPager;

/**
 * Created by heqiang on 17/10/16.
 */

public class WrapLinearlayout extends LinearLayout {
    private VerticalViewPager mViewPgaer;
    private MotionEvent lastEv;
    public WrapLinearlayout(Context context) {
        super(context);
    }

    public WrapLinearlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapLinearlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setViewPgaer(VerticalViewPager viewPgaer) {
        this.mViewPgaer = mViewPgaer;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(mViewPgaer != null) {
            if(lastEv == null){
                lastEv = ev;
            }
//            int dx = (int) (ev.getRawX() - lastEv.getRawX());
//            int dy = (int) (ev.getRawY() - lastEv.getRawY());
            ev.setLocation(mViewPgaer.getLeft()  + 1+ (lastEv.getRawX() - ev.getRawX())  , mViewPgaer.getTop() + (lastEv.getRawY() - ev.getRawY()) + 1);
            return mViewPgaer.onTouchEvent(ev);
        }
        else {
            return super.dispatchTouchEvent(ev);
        }
    }
}
